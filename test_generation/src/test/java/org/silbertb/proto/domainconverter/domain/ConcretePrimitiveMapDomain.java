package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.ConcretePrimitiveMapProto;
import lombok.Data;

import java.util.TreeMap;

@Data
@ProtoClass(protoClass = ConcretePrimitiveMapProto.class)
public class ConcretePrimitiveMapDomain {
    @ProtoField
    private TreeMap<Integer, Long> primitiveMap;
}
