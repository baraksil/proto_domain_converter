package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.PrimitiveTest;
import lombok.Data;

@Data
@ProtoClass(protoClass = PrimitiveTest.class)
public class PrimitiveDomain {
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
