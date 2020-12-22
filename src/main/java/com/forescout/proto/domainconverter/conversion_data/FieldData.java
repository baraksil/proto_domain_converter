package com.forescout.proto.domainconverter.conversion_data;

public class FieldData {
    public ConversionData.FieldType fieldType;
    public String protoFieldMethodSuffix;
    public String domainFieldMethodSuffix;
    public String dataStructureConcreteType;

    public String checkNotNullCommand() {
        switch (fieldType) {
            case MESSAGE:
            case PRIMITIVE_LIST:
            case MESSAGE_LIST:
            case PRIMITIVE_MAP:
            case MAP_TO_MESSAGE:
            case STRING:
            case BYTES:
                return "if(domain.get" + domainFieldMethodSuffix + "() != null) {";
            case OTHER:
            case BOOLEAN:
                return "";
            default:
                throw new RuntimeException("Unhandled field type: " + fieldType);
        }
    }

    public String closeCheckNotNullCommand() {
        switch (fieldType) {
            case MESSAGE:
            case PRIMITIVE_LIST:
            case MESSAGE_LIST:
            case PRIMITIVE_MAP:
            case MAP_TO_MESSAGE:
            case STRING:
            case BYTES:
                return "}";
            case OTHER:
            case BOOLEAN:
                return "";
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
        switch (fieldType) {
            case MESSAGE:
                return "if(proto.has" + protoFieldMethodSuffix + "()) {";
            case PRIMITIVE_LIST:
            case MESSAGE_LIST:
            case PRIMITIVE_MAP:
            case MAP_TO_MESSAGE:
            case OTHER:
            case STRING:
            case BYTES:
            case BOOLEAN:
                return "";
            default:
                throw new RuntimeException("Unhandled field type: " + fieldType);
        }
    }

    public String closeProtoHasValueCommand() {
        switch (fieldType) {
            case MESSAGE:
                return "}";
            case PRIMITIVE_LIST:
            case MESSAGE_LIST:
            case PRIMITIVE_MAP:
            case MAP_TO_MESSAGE:
            case STRING:
            case BYTES:
            case OTHER:
            case BOOLEAN:
                return "";
            default:
                throw new RuntimeException("Unhandled field type: " + fieldType);
        }
    }

    public String setInDomainCommand() {
        switch (fieldType) {
            case BOOLEAN:
            case STRING:
            case OTHER:
                return "domain.set" + domainFieldMethodSuffix + "(proto.get" + protoFieldMethodSuffix + "())";
            case PRIMITIVE_MAP:
                return "domain.set" + domainFieldMethodSuffix + "(new " + dataStructureConcreteType + "<>(proto.get" + protoFieldMethodSuffix + "()))";
            case MESSAGE:
                return "domain.set" + domainFieldMethodSuffix + "(toDomain(proto.get" + protoFieldMethodSuffix + "()))";
            case PRIMITIVE_LIST:
                return "domain.set" + domainFieldMethodSuffix + "(new " + dataStructureConcreteType + "<>(proto.get" + protoFieldMethodSuffix + "List()))";
            case MESSAGE_LIST:
                return "domain.setMessageList(proto.getMessageListList().stream().map(item -> toDomain(item)).collect(Collectors.toCollection(" + dataStructureConcreteType + "::new)))";
            case MAP_TO_MESSAGE:
                return "domain.set"+ domainFieldMethodSuffix + "(proto.get" + protoFieldMethodSuffix + "Map().entrySet().stream().collect(Collectors.toMap(e -> e.getKey(), e -> toDomain(e.getValue()), (v1, v2) -> v1, " + dataStructureConcreteType + "::new)))";
            case BYTES:
                return "domain.set" + domainFieldMethodSuffix + "(proto.get" + protoFieldMethodSuffix + "().toByteArray())";
            default:
                throw new RuntimeException("Unhandled field type: " + fieldType);
        }
    }
}
