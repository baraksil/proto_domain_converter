package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.SimpleContainerProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = SimpleContainerProto.class)
public class SimpleContainerDomain {
    @ProtoField
    private String strValue;

    @ProtoField
    private PrimitiveDomain primitives;
}
