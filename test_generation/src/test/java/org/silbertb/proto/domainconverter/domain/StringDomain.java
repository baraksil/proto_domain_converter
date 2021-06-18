package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.StringProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = StringProto.class)
public class StringDomain {
    @ProtoField
    private String stringValue;
}
