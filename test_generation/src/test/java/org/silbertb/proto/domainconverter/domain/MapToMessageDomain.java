package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.MapToMessageProto;
import lombok.Data;

import java.util.Map;

@Data
@ProtoClass(protoClass = MapToMessageProto.class)
public class MapToMessageDomain {

    @ProtoField
    Map<String, PrimitiveDomain> mapToMessage;
}
