package org.silbertb.proto.domainconverter;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.silbertb.proto.domainconverter.annotations.*;
import org.silbertb.proto.domainconverter.conversion_data.*;
import org.silbertb.proto.domainconverter.custom.NullMapper;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@SupportedAnnotationTypes({"org.silbertb.proto.domainconverter.annotations.ProtoClass"})
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
        if(conversionData.classesData().isEmpty()) {
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
                conversionData.converterPackage() + "." + conversionData.converterClass());
        try (PrintWriter out = new PrintWriter(fileObject.openWriter())) {
            m.execute(out, conversionData);
            out.flush();
        }
    }
    private ConversionData createConversionData(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        String converterName = processingEnv.getOptions().get("proto.domain.converter.name");
        if(converterName == null) {
            converterName = "org.silbertb.proto.domainconverter.generated.ProtoDomainConverter";
        }

        ConversionData.ConversionDataBuilder conversionData =
                ConversionData.builder()
                        .generator(this.getClass().getName())
                        .converterClass(StringUtils.getSimpleName(converterName))
                        .converterPackage(StringUtils.getPackage(converterName));

        ArrayList<ClassData> classesData = new ArrayList<>();

        for ( TypeElement annotation : annotations ) {
            for (Element domainElement : roundEnv.getElementsAnnotatedWith(annotation)) {
                ClassData classData = createClassData((TypeElement )domainElement);
                classesData.add(classData);
            }
        }

        return conversionData.classesData(classesData).build();
    }

    private ClassData createClassData(TypeElement  domainElement) {
        ProtoClass protoClassAnnotation = domainElement.getAnnotation(ProtoClass.class);
        TypeMirror protoClass = langModelUtil.getClassFromAnnotation(protoClassAnnotation::protoClass);


        ClassData.ClassDataBuilder classData = ClassData.builder()
                .domainClass(domainElement.getSimpleName().toString())
                .domainFullName(domainElement.getQualifiedName().toString())
                .protoFullName(protoClass.toString())
                .protoClass(StringUtils.getSimpleName(protoClass.toString()));

        TypeMirror mapperClass = langModelUtil.getClassFromAnnotation(protoClassAnnotation::mapper);
        String mapperFullName = mapperClass.toString();

        if(!mapperFullName.equals(NullMapper.class.getName())) {
            return classData
                    .mapperFullName(mapperFullName)
                    .mapperClass(StringUtils.getSimpleName(mapperFullName))
                    .constructorParameters(Collections.emptyList())
                    .fieldsData(Collections.emptyList())
                    .oneofBaseFieldsData(Collections.emptyList())
                    .build();
        }

        classData.constructorParameters(getConstructorParametersData(domainElement));

        ArrayList<FieldData> fieldDataList = new ArrayList<>();
        ArrayList<OneofBaseFieldData> oneofBaseFieldsDataList = new ArrayList<>();
        for(Element field : getDomainFields(domainElement, protoClassAnnotation.withInheritedFields())) {
            FieldData fieldData = createFieldData((VariableElement)field);
            if(fieldData != null) {
                fieldDataList.add(fieldData);
            }

            OneofBaseFieldData oneofBaseFieldData = createOneofBaseFieldData((VariableElement)field);
            if(oneofBaseFieldData != null) {
                oneofBaseFieldsDataList.add(oneofBaseFieldData);
            }

            if(fieldData != null && oneofBaseFieldData != null) {
                throw new IllegalArgumentException("field is annotated with both 'ProtoField' and 'OneofField'. field: " + field);
            }
        }

        classData.fieldsData(fieldDataList).oneofBaseFieldsData(oneofBaseFieldsDataList);

        OneofBase oneofBaseAnnotation = domainElement.getAnnotation(OneofBase.class);
        classData.oneofBaseClassData(createOneofBaseClassData(oneofBaseAnnotation));

        return classData.build();
    }

    private List<ParameterData> getConstructorParametersData(final TypeElement domainElement) {
        List<ParameterData> constructorParameters = new ArrayList<>();
        List<Element> constructors = domainElement.getEnclosedElements().stream()
                .filter(e -> e.getKind().equals(ElementKind.CONSTRUCTOR) && e.getModifiers().contains(Modifier.PUBLIC))
                .collect(Collectors.toList());
        for(Element constructor : constructors) {
            ProtoConstructor protoConstructorAnnotation = constructor.getAnnotation(ProtoConstructor.class);
            if(protoConstructorAnnotation == null) {
                continue;
            }

            if(!constructorParameters.isEmpty()) {
                throw new IllegalArgumentException("More than one constructors are annotated with @ProtoConstructor. class: " + domainElement);
            }
            for(VariableElement param : ((ExecutableElement)constructor).getParameters()) {
                FieldData fieldData = createFieldData(param);
                if(fieldData != null) {
                    ParameterData parameterData = new ParameterData();
                    parameterData.fieldData = fieldData;
                    constructorParameters.add(parameterData);
                }

                OneofBaseFieldData oneofBaseFieldData = createOneofBaseFieldData(param);
                if(oneofBaseFieldData != null) {
                    ParameterData parameterData = new ParameterData();
                    parameterData.oneofFieldData = oneofBaseFieldData;
                    constructorParameters.add(parameterData);
                }

                if(fieldData != null && oneofBaseFieldData != null) {
                    throw new IllegalArgumentException("constructor parameter is annotated with both 'ProtoField' and 'OneofField'. param: " + param);
                }

                if(fieldData == null && oneofBaseFieldData == null) {
                    throw new IllegalArgumentException("constructor parameter is not annotated with either 'ProtoField' and 'OneofField'. param: " + param);
                }
            }
            if(constructorParameters.size() > 0) {
                constructorParameters.get(constructorParameters.size()-1).isLast = true;
            }
        }
        return constructorParameters;
    }
    private OneofBaseClassData createOneofBaseClassData(OneofBase oneofBaseAnnotation) {
        if(oneofBaseAnnotation == null) {
            return null;
        }

        OneofBaseClassData oneofBaseClassData = new OneofBaseClassData(
                StringUtils.snakeCaseToPascalCase(oneofBaseAnnotation.oneofName()),
                createOneofFieldDataList(oneofBaseAnnotation, null)
                );

        return oneofBaseClassData;
    }

    private OneofBaseFieldData createOneofBaseFieldData(VariableElement field) {
        OneofBase oneofBaseAnnotation = field.getAnnotation(OneofBase.class);
        if(oneofBaseAnnotation == null) {
            return null;
        }

        String oneofBaseField = StringUtils.capitalize(field.getSimpleName().toString());
        OneofBaseFieldData.OneofBaseFieldDataBuilder oneofBaseFieldData = OneofBaseFieldData.builder();
        oneofBaseFieldData
                .oneofProtoName(
                        oneofBaseAnnotation.oneofName().equals("") ?
                                StringUtils.capitalize(field.getSimpleName().toString()) :
                                StringUtils.snakeCaseToPascalCase(oneofBaseAnnotation.oneofName()))
                .oneofBaseField(oneofBaseField)
                .oneOfFieldsData(createOneofFieldDataList(oneofBaseAnnotation, oneofBaseField))
                .domainFieldType(field.asType().toString());


        return oneofBaseFieldData.build();
    }

    private List<OneofFieldData> createOneofFieldDataList(OneofBase oneofBaseAnnotation, String oneofBaseField) {
        List<OneofFieldData> oneOfFieldsData = new ArrayList<>();
        for(OneofField oneofFieldAnnotation : oneofBaseAnnotation.oneOfFields()) {
            OneofFieldData oneofFieldData = createOneofFieldData(oneofFieldAnnotation, oneofBaseField);
            oneOfFieldsData.add(oneofFieldData);
        }
        return oneOfFieldsData;
    }

    private OneofFieldData createOneofFieldData(OneofField oneofFieldAnnotation, String oneofBaseField) {
        OneofFieldData oneofFieldData = new OneofFieldData();

        oneofFieldData.oneofFieldCase = oneofFieldAnnotation.protoField().toUpperCase();
        oneofFieldData.oneOfDomainField = StringUtils.capitalize(oneofFieldAnnotation.domainField());
        oneofFieldData.oneOfProtoField = StringUtils.snakeCaseToPascalCase(oneofFieldAnnotation.protoField());
        TypeMirror domainType = langModelUtil.getDomainClassFromAnnotation(oneofFieldAnnotation);
        oneofFieldData.oneofImplClass = domainType.toString();
        oneofFieldData.oneofImplClassSimple = ((DeclaredType)domainType).asElement().getSimpleName().toString();
        oneofFieldData.fieldIsMessage = isProtoMessage(processingEnv.getElementUtils().getTypeElement(oneofFieldData.oneofImplClass).asType());
        oneofFieldData.domainBaseField = oneofBaseField;

        return oneofFieldData;
    }

    private FieldData createFieldData(VariableElement field) {
        ProtoField protoFieldAnnotation = field.getAnnotation(ProtoField.class);
        if(protoFieldAnnotation == null) {
            return null;
        }

        TypeMirror fieldType = field.asType();
        FieldData.FieldDataBuilder fieldData = FieldData.builder();

        fieldData.domainFieldMethodSuffix(StringUtils.capitalize(field.getSimpleName().toString()))
                .protoFieldPascalCase(getProtoFieldMethodSuffix(field, protoFieldAnnotation))
                .fieldType(calculateFieldType(fieldType))
                .dataStructureConcreteType(calculateDataStructureConcreteType(field));

        ProtoConverter protoConverterAnnotation = field.getAnnotation(ProtoConverter.class);
        if(protoConverterAnnotation != null) {
            TypeMirror converterType = langModelUtil.getClassFromAnnotation(() -> protoConverterAnnotation.converter());

            fieldData.protoTypeForConverter(protoConverterAnnotation.protoType())
                    .converterFullName(converterType.toString())
                    .converterName(StringUtils.getSimpleName(converterType.toString()));
        }

        return fieldData.build();
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

    private FieldType calculateFieldType(TypeMirror fieldType) {
        if(fieldType.getKind().equals(TypeKind.BOOLEAN)) {
            return FieldType.BOOLEAN;
        }

        if(langModelUtil.isSameType(fieldType, String.class)) {
            return FieldType.STRING;
        }

        if(langModelUtil.isByteArray(fieldType)) {
            return FieldType.BYTES;
        }

        if(isProtoMessage(fieldType)) {
            return FieldType.MESSAGE;
        }

        if(langModelUtil.isList(fieldType)) {
            TypeMirror typeArgument = langModelUtil.getGenericsTypes(fieldType).get(0);
            if(isProtoMessage(typeArgument)) {
                return FieldType.MESSAGE_LIST;
            } else {
                return FieldType.PRIMITIVE_LIST;
            }
        }

        if(langModelUtil.isMap(fieldType)) {
            TypeMirror typeArgument = langModelUtil.getGenericsTypes(fieldType).get(1);
            if(isProtoMessage(typeArgument)) {
                return FieldType.MAP_TO_MESSAGE;
            } else {
                return FieldType.PRIMITIVE_MAP;
            }
        }

        return FieldType.OTHER;
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
