package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.StringProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = StringProto.class)
public class StringDomain {
    @ProtoField
    private String stringValue;
}
