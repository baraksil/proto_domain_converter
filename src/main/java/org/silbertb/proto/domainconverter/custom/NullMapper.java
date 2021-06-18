package org.silbertb.proto.domainconverter.custom;

import com.google.protobuf.Message;

public class NullMapper implements Mapper {
    @Override
    public Object toDomain(Message protoValue) {
        return null;
    }

    @Override
    public Message toProto(Object domainValue) {
        return null;
    }
}
