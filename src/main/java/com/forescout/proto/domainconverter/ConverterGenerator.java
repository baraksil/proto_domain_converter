package com.forescout.proto.domainconverter;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@SupportedAnnotationTypes({"com.forescout.proto.domainconverter.annotations.ProtoClass"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ConverterGenerator extends AbstractProcessor {
    private LangModelUtil langModelUtil;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        langModelUtil = new LangModelUtil(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        info("Processing ProtoClass annotation");
        ConversionData conversionData = createConversionData(annotations, roundEnv);
        if(conversionData.classesData.isEmpty()) {
            return true;
        }

        // Write source file
        try {
            writeSource(conversionData);
        } catch (IOException ex) {
            error("Failed to generate proto domain conversion class. Exception: " + ex);
        }

        return true;
    }

    private void writeSource(ConversionData conversionData) throws IOException {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile("converter.mustache");

        Filer filer = processingEnv.getFiler();
        JavaFileObject fileObject = filer.createSourceFile(
                conversionData.converterPackage + "." + conversionData.converterClass);
        try (PrintWriter out = new PrintWriter(fileObject.openWriter())) {
            m.execute(out, conversionData);
            out.flush();
        }
    }
    private ConversionData createConversionData(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        String converterName = processingEnv.getOptions().get("proto.domain.converter.name");
        if(converterName == null) {
            converterName = "com.forescout.proto.domainconverter.generated.ProtoDomainConverter";
        }

        ConversionData conversionData =
                new ConversionData(
                        this.getClass().getName(),
                        StringUtils.getPackage(converterName),
                        StringUtils.getSimpleName(converterName));
        for ( TypeElement annotation : annotations ) {
            for (Element domainElement : roundEnv.getElementsAnnotatedWith(annotation)) {
                ConversionData.ClassData classData = createClassData((TypeElement )domainElement);
                conversionData.classesData.add(classData);
            }
        }
        return conversionData;
    }

    private ConversionData.ClassData createClassData(TypeElement  domainElement) {
        ConversionData.ClassData classData = new ConversionData.ClassData();
        classData.domainClass = domainElement.getSimpleName().toString();
        classData.domainFullName = domainElement.getQualifiedName().toString();

        ProtoClass protoClassAnnotation = domainElement.getAnnotation(ProtoClass.class);
        TypeMirror protoClass = langModelUtil.getProtoClassFromAnnotation(protoClassAnnotation);

        classData.protoFullName = protoClass.toString();
        classData.protoClass = StringUtils.getSimpleName(classData.protoFullName);

        for(Element field : getDomainFields(domainElement, protoClassAnnotation.withInheritedFields())) {
            ConversionData.FieldData fieldData = createFieldData((VariableElement)field);
            if(fieldData != null) {
                classData.fieldsData.add(fieldData);
            }
        }

        return classData;
    }

    private ConversionData.FieldData createFieldData(VariableElement field) {
        ProtoField protoFieldAnnotation = field.getAnnotation(ProtoField.class);
        if(protoFieldAnnotation == null) {
            return null;
        }
        TypeMirror fieldType = field.asType();
        ConversionData.FieldData fieldData = new ConversionData.FieldData();

        fieldData.domainFieldMethodSuffix = StringUtils.capitalize(field.getSimpleName().toString());
        fieldData.protoFieldMethodSuffix = getProtoFieldMethodSuffix(field, protoFieldAnnotation);
        fieldData.fieldType = calculateFieldType(fieldType);


        return fieldData;
    }

    private ConversionData.FieldType calculateFieldType(TypeMirror fieldType) {
        if(fieldType.getKind().equals(TypeKind.BOOLEAN)) {
            return ConversionData.FieldType.BOOLEAN;
        }

        if(isProtoMessage(fieldType)) {
            return ConversionData.FieldType.MESSAGE;
        }

        if(langModelUtil.isList(fieldType)) {
            TypeMirror typeArgument = langModelUtil.getGenericsTypes(fieldType).get(0);
            if(isProtoMessage(typeArgument)) {
                return ConversionData.FieldType.MESSAGE_LIST;
            } else {
                return ConversionData.FieldType.PRIMITIVE_LIST;
            }
        }

        return ConversionData.FieldType.OTHER;
    }

    private boolean isProtoMessage(TypeMirror fieldType) {
        if(fieldType.getKind().equals(TypeKind.DECLARED)) {
            return langModelUtil.getAnnotation(fieldType, ProtoClass.class) != null;
        }

        return false;
    }

    private String getProtoFieldMethodSuffix(VariableElement field, ProtoField protoFieldAnnotation) {
        return  protoFieldAnnotation.protoName().equals("") ?
                StringUtils.capitalize(field.getSimpleName().toString()):
                StringUtils.lowerUnderscoreToPascalCase(protoFieldAnnotation.protoName());
    }

    private List<Element> getDomainFields(final TypeElement domainElement, boolean withInheritedFields) {
        List<Element> fields = domainElement.getEnclosedElements().stream().filter(e -> e.getKind().equals(ElementKind.FIELD)).collect(Collectors.toList());

        if (withInheritedFields) {
            if (domainElement.getSuperclass().getKind() == TypeKind.DECLARED) {
                TypeElement superclass = (TypeElement) processingEnv.getTypeUtils().asElement(domainElement.getSuperclass());
                fields.addAll(getDomainFields(superclass, true));
            }
        }
        return fields;
    }

    private void info(String msg) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, msg);
    }

    private void error(String msg) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, msg);
    }
}
