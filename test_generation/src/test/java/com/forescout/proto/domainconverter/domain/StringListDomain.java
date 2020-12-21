package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.StringListProto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ProtoClass(protoClass = StringListProto.class)
public class StringListDomain {

    @ProtoField
    private List<String> stringList;
}
