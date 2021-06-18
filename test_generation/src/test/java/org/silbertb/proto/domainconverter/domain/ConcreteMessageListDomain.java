package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.ConcreteMessageListProto;
import lombok.Data;

import java.util.LinkedList;

@Data
@ProtoClass(protoClass = ConcreteMessageListProto.class)
public class ConcreteMessageListDomain {

    @ProtoField
    private LinkedList<PrimitiveDomain> messageList;
}
