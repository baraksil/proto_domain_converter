package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.ConcretePrimitiveListProto;
import lombok.Data;

import java.util.LinkedList;

@Data
@ProtoClass(protoClass = ConcretePrimitiveListProto.class)
public class ConcretePrimitiveListDomain {

    @ProtoField
    private LinkedList<Integer> intList;
}
