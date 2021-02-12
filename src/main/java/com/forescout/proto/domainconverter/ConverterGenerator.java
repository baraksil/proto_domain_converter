package com.forescout.proto.domainconverter;

import com.forescout.proto.domainconverter.annotations.*;
import com.forescout.proto.domainconverter.conversion_data.*;
import com.forescout.proto.domainconverter.custom.NullMapper;
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
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
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
        TypeMirror protoClass = langModelUtil.getClassFromAnnotation(protoClassAnnotation::protoClass);

        classData.protoFullName = protoClass.toString();
        classData.protoClass = StringUtils.getSimpleName(classData.protoFullName);
        TypeMirror mapperClass = langModelUtil.getClassFromAnnotation(protoClassAnnotation::mapper);
        String mapperFullName = mapperClass.toString();

        classData.mapperFullName = mapperFullName.equals(NullMapper.class.getName()) ? null : mapperFullName;
        classData.mapperClass = classData.mapperFullName == null ? null : StringUtils.getSimpleName(mapperFullName);

        if(classData.mapperFullName != null) {
            return classData;
        }

        for(Element field : getDomainFields(domainElement, protoClassAnnotation.withInheritedFields())) {
            FieldData fieldData = createFieldData((VariableElement)field);
            if(fieldData != null) {
                classData.fieldsData.add(fieldData);
            }

            OneofBaseFieldData oneofBaseFieldData = createOneofBaseFieldData((VariableElement)field);
            if(oneofBaseFieldData != null) {
                classData.oneofBaseFieldsData.add(oneofBaseFieldData);
            }

            if(fieldData != null && oneofBaseFieldData != null) {
                throw new IllegalArgumentException("field is annotated with both 'ProtoField' and 'OneofField'. field: " + field);
            }
        }

        OneofBase oneofBaseAnnotation = domainElement.getAnnotation(OneofBase.class);
        classData.oneofBaseClassData = createOneofBaseClassData(oneofBaseAnnotation);

        return classData;
    }

    private OneofBaseClassData createOneofBaseClassData(OneofBase oneofBaseAnnotation) {
        if(oneofBaseAnnotation == null) {
            return null;
        }

        OneofBaseClassData oneofBaseClassData = new OneofBaseClassData();
        oneofBaseClassData.oneofProtoName = StringUtils.snakeCaseToPascalCase(oneofBaseAnnotation.oneofName());
        oneofBaseClassData.oneOfFieldsData = createOneofFieldDataList(oneofBaseAnnotation);

        return oneofBaseClassData;
    }

    private OneofBaseFieldData createOneofBaseFieldData(VariableElement field) {
        OneofBase oneofBaseAnnotation = field.getAnnotation(OneofBase.class);
        if(oneofBaseAnnotation == null) {
            return null;
        }

        OneofBaseFieldData oneofBaseFieldData = new OneofBaseFieldData();
        oneofBaseFieldData.oneofProtoName = oneofBaseAnnotation.oneofName().equals("") ?
                StringUtils.capitalize(field.getSimpleName().toString()) :
                StringUtils.snakeCaseToPascalCase(oneofBaseAnnotation.oneofName());
        oneofBaseFieldData.oneofBaseField = StringUtils.capitalize(field.getSimpleName().toString());

        oneofBaseFieldData.oneOfFieldsData = createOneofFieldDataList(oneofBaseAnnotation);
        for(OneofFieldData oneofFieldData : oneofBaseFieldData.oneOfFieldsData) {
            oneofFieldData.domainBaseField = oneofBaseFieldData.oneofBaseField;
        }

        return oneofBaseFieldData;
    }

    private List<OneofFieldData> createOneofFieldDataList(OneofBase oneofBaseAnnotation) {
        List<OneofFieldData> oneOfFieldsData = new ArrayList<>();
        for(OneofField oneofFieldAnnotation : oneofBaseAnnotation.oneOfFields()) {
            OneofFieldData oneofFieldData = createOneofFieldData(oneofFieldAnnotation);
            oneOfFieldsData.add(oneofFieldData);
        }
        return oneOfFieldsData;
    }

    private OneofFieldData createOneofFieldData(OneofField oneofFieldAnnotation) {
        OneofFieldData oneofFieldData = new OneofFieldData();

        oneofFieldData.oneofFieldCase = oneofFieldAnnotation.protoField().toUpperCase();
        oneofFieldData.oneOfDomainField = StringUtils.capitalize(oneofFieldAnnotation.domainField());
        oneofFieldData.oneOfProtoField = StringUtils.snakeCaseToPascalCase(oneofFieldAnnotation.protoField());
        TypeMirror domainType = langModelUtil.getDomainClassFromAnnotation(oneofFieldAnnotation);
        oneofFieldData.oneofImplClass = domainType.toString();
        oneofFieldData.oneofImplClassSimple = ((DeclaredType)domainType).asElement().getSimpleName().toString();
        oneofFieldData.fieldIsMessage = isProtoMessage(processingEnv.getElementUtils().getTypeElement(oneofFieldData.oneofImplClass).asType());


        return oneofFieldData;
    }

    private FieldData createFieldData(VariableElement field) {
        ProtoField protoFieldAnnotation = field.getAnnotation(ProtoField.class);
        if(protoFieldAnnotation == null) {
            return null;
        }
        TypeMirror fieldType = field.asType();
        FieldData fieldData = new FieldData();

        fieldData.domainFieldMethodSuffix = StringUtils.capitalize(field.getSimpleName().toString());
        fieldData.protoFieldMethodSuffix = getProtoFieldMethodSuffix(field, protoFieldAnnotation);
        fieldData.fieldType = calculateFieldType(fieldType);
        fieldData.dataStructureConcreteType = calculateDataStructureConcreteType(field);

        ProtoConverter protoConverterAnnotation = field.getAnnotation(ProtoConverter.class);
        if(protoConverterAnnotation != null) {
            fieldData.protoTypeForConverter = protoConverterAnnotation.protoType();
            TypeMirror converterType = langModelUtil.getClassFromAnnotation(() -> protoConverterAnnotation.converter());
            fieldData.converterFullName = converterType.toString();
            fieldData.converterClass = StringUtils.getSimpleName(fieldData.converterFullName);
        }
        return fieldData;
    }

    private String calculateDataStructureConcreteType(VariableElement field) {
        TypeMirror fieldType = field.asType();

        if(langModelUtil.isList(fieldType)) {
            if(langModelUtil.isConcreteType(field)) {
                return processingEnv.getTypeUtils().erasure(fieldType).toString();
            }

            return ArrayList.class.getName();
        }

        if(langModelUtil.isMap(fieldType)) {
            if(langModelUtil.isConcreteType(field)) {
                return processingEnv.getTypeUtils().erasure(fieldType).toString();
            }

            if(langModelUtil.isAssignedFrom(fieldType, SortedMap.class)) {
                return TreeMap.class.getName();
            }

            return HashMap.class.getName();
        }

        return null;
    }

    private ConversionData.FieldType calculateFieldType(TypeMirror fieldType) {
        if(fieldType.getKind().equals(TypeKind.BOOLEAN)) {
            return ConversionData.FieldType.BOOLEAN;
        }

        if(langModelUtil.isSameType(fieldType, String.class)) {
            return ConversionData.FieldType.STRING;
        }

        if(langModelUtil.isByteArray(fieldType)) {
            return ConversionData.FieldType.BYTES;
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

        if(langModelUtil.isMap(fieldType)) {
            TypeMirror typeArgument = langModelUtil.getGenericsTypes(fieldType).get(1);
            if(isProtoMessage(typeArgument)) {
                return ConversionData.FieldType.MAP_TO_MESSAGE;
            } else {
                return ConversionData.FieldType.PRIMITIVE_MAP;
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
                StringUtils.snakeCaseToPascalCase(protoFieldAnnotation.protoName());
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
