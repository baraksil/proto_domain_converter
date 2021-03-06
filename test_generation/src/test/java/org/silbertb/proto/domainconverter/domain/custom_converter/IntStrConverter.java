package org.silbertb.proto.domainconverter.domain.custom_converter;

import org.silbertb.proto.domainconverter.custom.TypeConverter;

public class IntStrConverter implements TypeConverter<String, Integer> {
    @Override
    public String toDomainValue(Integer protoValue) {
        return protoValue.toString();
    }

    @Override
    public boolean shouldAssignToProto(String domainValue) {
        return domainValue != null;
    }

    @Override
    public Integer toProtobufValue(String domainValue) {
        return Integer.parseInt(domainValue);
    }
}
