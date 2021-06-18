package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.ConcreteMapToMessageProto;
import lombok.Data;

import java.util.HashMap;

@Data
@ProtoClass(protoClass = ConcreteMapToMessageProto.class)
public class ConcreteMapToMessageDomain {
    @ProtoField
    HashMap<String, PrimitiveDomain> mapToMessage;
}
