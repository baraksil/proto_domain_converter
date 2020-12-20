package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.PrimitiveListProto;
import lombok.Data;

import java.util.ArrayList;

@Data
@ProtoClass(protoClass = PrimitiveListProto.class)
public class PrimitiveListDomain {

    @ProtoField
    private ArrayList<Integer> listInt;
}
