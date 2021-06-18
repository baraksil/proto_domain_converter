package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.PrimitiveListProto;
import lombok.Data;

import java.util.List;

@Data
@ProtoClass(protoClass = PrimitiveListProto.class)
public class PrimitiveListDomain {

    @ProtoField
    private List<Integer> intList;
}
