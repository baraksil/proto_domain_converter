package org.silbertb.proto.domainconverter.conversion_data;

import org.silbertb.proto.domainconverter.custom.ProtoType;

public class FieldData {
    public ConversionData.FieldType fieldType;
    public String protoFieldMethodSuffix;
    public String domainFieldMethodSuffix;
    public String dataStructureConcreteType;

    public String converterClass;
    public String converterFullName;
    public ProtoType protoTypeForConverter;

    public boolean hasConverter() {
        return converterClass != null;
    }

    public String converterName() {
        return converterClass;
    }

    private String protoBuilderSetCommand() {
        switch (protoTypeForConverter) {
            case MAP:
                return "putAll" + protoFieldMethodSuffix;
            case LIST:
                return "addAll" + protoFieldMethodSuffix;
            case OTHER:
                return "set" + protoFieldMethodSuffix;
            default:
                throw new RuntimeException("Unhandled proto type: " + fieldType);
        }
    }

    private String domainGetterMethodPrefix() {
        if(fieldType.equals(ConversionData.FieldType.BOOLEAN)) {
            return "is";
        }

        return "get";
    }

    public String domainGetterMethod() {
       return domainGetterMethodPrefix() +  domainFieldMethodSuffix;
    }

    public String addToBuilderUsingConverter() {
        return "builder." + protoBuilderSetCommand() + "(converter.toProtobufValue(domain." + domainGetterMethod() + "()))";
    }

    private String protoGetterSuffix() {
        switch (protoTypeForConverter) {
            case LIST:
                return "List";
            case MAP:
                return "Map";
            case OTHER:
                return "";
            default:
                throw new RuntimeException("Unhandled proto type: " + protoTypeForConverter);
        }
    }

    public String protoGetterMethod() {
        return "get" + protoFieldMethodSuffix + protoGetterSuffix();
    }

    public String setInDomainUsingConverter() {
        return "domain.set" + domainFieldMethodSuffix + "(converter.toDomainValue(proto." + protoGetterMethod() + "()))";
    }

    public String checkNotNullCommand() {
        if(isNullableDomainType()) {
            return "if(domain.get" + domainFieldMethodSuffix + "() != null) {";
        }

        return "";
    }

    public String closeCheckNotNullCommand() {
        return isNullableDomainType() ? "}" : "";
    }

    private boolean isNullableDomainType() {
        switch (fieldType) {
            case MESSAGE:
            case PRIMITIVE_LIST:
            case MESSAGE_LIST:
            case PRIMITIVE_MAP:
            case MAP_TO_MESSAGE:
            case STRING:
            case BYTES:
                return true;
            case OTHER:
            case BOOLEAN:
                return false;
            default:
                throw new RuntimeException("Unhandled field type: " + fieldType);
        }
    }

    public String addToBuilderCommand() {
        switch (fieldType) {
            case BOOLEAN:
                return "builder.set" + protoFieldMethodSuffix + "(domain.is" + domainFieldMethodSuffix + "())";
            case MESSAGE:
                return "builder.set" + protoFieldMethodSuffix + "(toProto(domain.get" + domainFieldMethodSuffix + "()))";
            case PRIMITIVE_LIST:
                return "builder.addAll" + protoFieldMethodSuffix + "(domain.get" + domainFieldMethodSuffix + "())";
            case MESSAGE_LIST:
                return "domain.get" + domainFieldMethodSuffix + "().forEach(item -> builder.add" + protoFieldMethodSuffix + "(toProto(item)))";
            case PRIMITIVE_MAP:
                return "builder.putAll" + protoFieldMethodSuffix + "(domain.get" + domainFieldMethodSuffix + "())";
            case MAP_TO_MESSAGE:
                return "domain.get" + domainFieldMethodSuffix + "().forEach((key, value) -> builder.put" + protoFieldMethodSuffix + "(key, toProto(value)))";
            case BYTES:
                return "builder.set" + protoFieldMethodSuffix + "(ByteString.copyFrom(domain.get" + domainFieldMethodSuffix + "()))";
            case STRING:
            case OTHER:
                return "builder.set" + protoFieldMethodSuffix + "(domain.get" + domainFieldMethodSuffix + "())";
            default:
                throw new RuntimeException("Unhandled field type: " + fieldType);
        }
    }

    public String checkProtoHasValueCommand() {
        if(isNullableProtoType()) {
            return "if(proto.has" + protoFieldMethodSuffix + "()) {";
        }
        return "";
    }

    public String closeProtoHasValueCommand() {
        return isNullableProtoType() ? "}" : "";
    }

    public boolean isNullableProtoType() {
        return fieldType.equals(ConversionData.FieldType.MESSAGE);
    }

    public String setInDomainCommand() {
        return "domain.set" + domainFieldMethodSuffix + "(" + convertProtoValue() + ")";
    }

    public String convertProtoValue() {
        switch (fieldType) {
            case BOOLEAN:
            case STRING:
            case OTHER:
                return "proto.get" + protoFieldMethodSuffix + "()";
            case PRIMITIVE_MAP:
                return "new " + dataStructureConcreteType + "<>(proto.get" + protoFieldMethodSuffix + "())";
            case MESSAGE:
                return "toDomain(proto.get" + protoFieldMethodSuffix + "())";
            case PRIMITIVE_LIST:
                return "new " + dataStructureConcreteType + "<>(proto.get" + protoFieldMethodSuffix + "List())";
            case MESSAGE_LIST:
                return "proto.getMessageListList().stream().map(item -> toDomain(item)).collect(Collectors.toCollection(" + dataStructureConcreteType + "::new))";
            case MAP_TO_MESSAGE:
                return "proto.get" + protoFieldMethodSuffix + "Map().entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> toDomain(e.getValue()), (v1, v2) -> v1, " + dataStructureConcreteType + "::new))";
            case BYTES:
                return "proto.get" + protoFieldMethodSuffix + "().toByteArray()";
            default:
                throw new RuntimeException("Unhandled field type: " + fieldType);
        }
    }
}
