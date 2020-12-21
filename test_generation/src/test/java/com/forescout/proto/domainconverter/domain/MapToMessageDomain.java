package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.MapToMessageProto;
import lombok.Data;

import java.util.Map;

@Data
@ProtoClass(protoClass = MapToMessageProto.class)
public class MapToMessageDomain {

    @ProtoField
    Map<String, PrimitiveDomain> mapToMessage;
}
