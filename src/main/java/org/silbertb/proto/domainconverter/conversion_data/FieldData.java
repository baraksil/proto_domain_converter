package org.silbertb.proto.domainconverter.conversion_data;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.silbertb.proto.domainconverter.custom.ProtoType;

@Accessors(fluent = true)
@Getter
@Builder
public class FieldData {
    final private FieldType fieldType;
    final private String protoFieldPascalCase;
    final private String domainFieldMethodSuffix;
    final private String dataStructureConcreteType;

    final private String converterName;
    final private String converterFullName;
    final private ProtoType protoTypeForConverter;

    public boolean hasConverter() {
        return converterName != null;
    }

    private String protoBuilderSetCommand() {
        switch (protoTypeForConverter) {
            case MAP:
                return "putAll" + protoFieldPascalCase;
            case LIST:
                return "addAll" + protoFieldPascalCase;
            case OTHER:
                return "set" + protoFieldPascalCase;
            default:
                throw new RuntimeException("Unhandled proto type: " + fieldType);
        }
    }

    private String domainGetterMethodPrefix() {
        if(fieldType.equals(FieldType.BOOLEAN)) {
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
        return "get" + protoFieldPascalCase + protoGetterSuffix();
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
                return "builder.set" + protoFieldPascalCase + "(domain.is" + domainFieldMethodSuffix + "())";
            case MESSAGE:
                return "builder.set" + protoFieldPascalCase + "(toProto(domain.get" + domainFieldMethodSuffix + "()))";
            case PRIMITIVE_LIST:
                return "builder.addAll" + protoFieldPascalCase + "(domain.get" + domainFieldMethodSuffix + "())";
            case MESSAGE_LIST:
                return "domain.get" + domainFieldMethodSuffix + "().forEach(item -> builder.add" + protoFieldPascalCase + "(toProto(item)))";
            case PRIMITIVE_MAP:
                return "builder.putAll" + protoFieldPascalCase + "(domain.get" + domainFieldMethodSuffix + "())";
            case MAP_TO_MESSAGE:
                return "domain.get" + domainFieldMethodSuffix + "().forEach((key, value) -> builder.put" + protoFieldPascalCase + "(key, toProto(value)))";
            case BYTES:
                return "builder.set" + protoFieldPascalCase + "(ByteString.copyFrom(domain.get" + domainFieldMethodSuffix + "()))";
            case STRING:
            case OTHER:
                return "builder.set" + protoFieldPascalCase + "(domain.get" + domainFieldMethodSuffix + "())";
            default:
                throw new RuntimeException("Unhandled field type: " + fieldType);
        }
    }

    public String checkProtoHasValueCommand() {
        if(isNullableProtoType()) {
            return "if(proto.has" + protoFieldPascalCase + "()) {";
        }
        return "";
    }

    public String closeProtoHasValueCommand() {
        return isNullableProtoType() ? "}" : "";
    }

    public boolean isNullableProtoType() {
        return fieldType.equals(FieldType.MESSAGE);
    }

    public String setInDomainCommand() {
        return "domain.set" + domainFieldMethodSuffix + "(" + convertProtoValue() + ")";
    }

    public String convertProtoValue() {
        switch (fieldType) {
            case BOOLEAN:
            case STRING:
            case OTHER:
                return "proto.get" + protoFieldPascalCase + "()";
            case PRIMITIVE_MAP:
                return "new " + dataStructureConcreteType + "<>(proto.get" + protoFieldPascalCase + "())";
            case MESSAGE:
                return "toDomain(proto.get" + protoFieldPascalCase + "())";
            case PRIMITIVE_LIST:
                return "new " + dataStructureConcreteType + "<>(proto.get" + protoFieldPascalCase + "List())";
            case MESSAGE_LIST:
                return "proto.getMessageListList().stream().map(item -> toDomain(item)).collect(Collectors.toCollection(" + dataStructureConcreteType + "::new))";
            case MAP_TO_MESSAGE:
                return "proto.get" + protoFieldPascalCase + "Map().entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> toDomain(e.getValue()), (v1, v2) -> v1, " + dataStructureConcreteType + "::new))";
            case BYTES:
                return "proto.get" + protoFieldPascalCase + "().toByteArray()";
            default:
                throw new RuntimeException("Unhandled field type: " + fieldType);
        }
    }
}
