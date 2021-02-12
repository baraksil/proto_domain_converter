package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.domain.oneof.field.OneofBaseFieldDomain;
import com.forescout.proto.domainconverter.test.proto.PrimitivesProto;
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
