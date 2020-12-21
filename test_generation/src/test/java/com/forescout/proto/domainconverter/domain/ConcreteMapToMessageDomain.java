package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.ConcreteMapToMessageProto;
import lombok.Data;

import java.util.Map;

@Data
@ProtoClass(protoClass = ConcreteMapToMessageProto.class)
public class ConcreteMapToMessageDomain {
    @ProtoField
    Map<String, PrimitiveDomain> mapToMessage;
}
