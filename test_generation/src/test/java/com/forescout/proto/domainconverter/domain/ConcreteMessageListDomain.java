package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.ConcreteMessageListProto;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
@ProtoClass(protoClass = ConcreteMessageListProto.class)
public class ConcreteMessageListDomain {

    @ProtoField
    private LinkedList<PrimitiveDomain> messageList;
}
