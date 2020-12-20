package com.forescout.proto.domainconverter;

import java.util.ArrayList;
import java.util.List;

public class ConversionData {
    String generator;
    String converterPackage;
    String converterClass;
    List<ClassData> classesData = new ArrayList<>();

    public ConversionData(String generator, String converterPackage, String converterClass) {
        this.converterPackage = converterPackage;
        this.converterClass = converterClass;
        this.generator = generator;
    }

    static class ClassData {
        String domainFullName;
        String domainClass;
        String protoFullName;
        String protoClass;
        List<FieldData> fieldsData = new ArrayList<>();
    }

    enum FieldType {
        MESSAGE,
        BOOLEAN,
        PRIMITIVE_LIST,
        MESSAGE_LIST,
        OTHER
    }
    static class FieldData {
        FieldType fieldType;
        String protoFieldMethodSuffix;
        String domainFieldMethodSuffix;

        String addToBuilderCommand() {
            switch (fieldType) {
                case BOOLEAN:
                    return "builder.set" + protoFieldMethodSuffix + "(domain.is" + domainFieldMethodSuffix + "())";
                case MESSAGE:
                    return "builder.set" + protoFieldMethodSuffix + "(toProto(domain.get" + domainFieldMethodSuffix + "()))";
                case PRIMITIVE_LIST:
                    return "builder.addAll" + protoFieldMethodSuffix + "(domain.get" + domainFieldMethodSuffix + "())";
                case MESSAGE_LIST:
                    return "domain.get" + domainFieldMethodSuffix + "().forEach(item -> builder.add" + protoFieldMethodSuffix + "(toProto(item)))";
                case OTHER:
                    return "builder.set" + protoFieldMethodSuffix + "(domain.get" + domainFieldMethodSuffix + "())";
                default:
                    throw new RuntimeException("Unhandled field type: " + fieldType);
            }
        }
    }
}
