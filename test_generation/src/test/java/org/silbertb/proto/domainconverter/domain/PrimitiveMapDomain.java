package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.PrimitiveMapProto;
import lombok.Data;

import java.util.Map;

@Data
@ProtoClass(protoClass = PrimitiveMapProto.class)
public class PrimitiveMapDomain {

    @ProtoField
    private Map<Integer, Long> primitiveMap;
}
