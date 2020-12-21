package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.PrimitiveMapProto;
import lombok.Data;

import java.util.Map;

@Data
@ProtoClass(protoClass = PrimitiveMapProto.class)
public class PrimitiveMapDomain {

    @ProtoField
    private Map<Integer, Long> primitiveMap;
}
