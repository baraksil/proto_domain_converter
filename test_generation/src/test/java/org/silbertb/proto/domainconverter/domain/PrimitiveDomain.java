package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.domain.oneof.field.OneofBaseFieldDomain;
import org.silbertb.proto.domainconverter.test.proto.PrimitivesProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = PrimitivesProto.class)
public class PrimitiveDomain implements OneofBaseFieldDomain {
    @ProtoField
    private long longValue;

    @ProtoField
    private int intValue;

    @ProtoField
    private float floatValue;

    @ProtoField
    private double doubleValue;

    @ProtoField
    private boolean booleanValue;
}
