package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.StringListProto;
import lombok.Data;

import java.util.List;

@Data
@ProtoClass(protoClass = StringListProto.class)
public class StringListDomain {

    @ProtoField
    private List<String> stringList;
}
