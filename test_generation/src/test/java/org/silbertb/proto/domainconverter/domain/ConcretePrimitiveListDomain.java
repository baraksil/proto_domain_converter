package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.ConcretePrimitiveListProto;
import lombok.Data;

import java.util.LinkedList;

@Data
@ProtoClass(protoClass = ConcretePrimitiveListProto.class)
public class ConcretePrimitiveListDomain {

    @ProtoField
    private LinkedList<Integer> intList;
}
