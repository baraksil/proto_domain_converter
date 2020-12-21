package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.PrimitiveListProto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ProtoClass(protoClass = PrimitiveListProto.class)
public class PrimitiveListDomain {

    @ProtoField
    private List<Integer> listInt;
}
