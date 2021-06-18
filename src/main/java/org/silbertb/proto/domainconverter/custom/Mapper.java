package org.silbertb.proto.domainconverter.custom;

import com.google.protobuf.Message;

public interface Mapper<T, E extends Message> {
    T toDomain(E protoValue);
    E toProto(T domainValue);
}
