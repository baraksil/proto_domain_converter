package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.ConcretePrimitiveMapProto;
import lombok.Data;

import java.util.Map;

@Data
@ProtoClass(protoClass = ConcretePrimitiveMapProto.class)
public class ConcretePrimitiveMapDomain {
    @ProtoField
    private Map<Integer, Long> primitiveMap;
}
