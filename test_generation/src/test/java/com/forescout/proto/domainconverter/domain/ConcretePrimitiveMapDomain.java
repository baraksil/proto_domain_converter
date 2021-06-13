package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.ConcretePrimitiveMapProto;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Data
@ProtoClass(protoClass = ConcretePrimitiveMapProto.class)
public class ConcretePrimitiveMapDomain {
    @ProtoField
    private TreeMap<Integer, Long> primitiveMap;
}
