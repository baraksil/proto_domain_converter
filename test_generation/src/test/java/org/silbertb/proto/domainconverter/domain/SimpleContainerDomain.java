package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.SimpleContainerProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = SimpleContainerProto.class)
public class SimpleContainerDomain {

    @ProtoField
    private PrimitiveDomain primitives;
}
