package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.OneofBase;
import com.forescout.proto.domainconverter.annotations.OneofField;
import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.domain.oneof.field.OneofBaseFieldDomain;
import com.forescout.proto.domainconverter.domain.oneof.field.OneofIntImplDomain;
import com.forescout.proto.domainconverter.test.proto.AllInOneProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = AllInOneProto.class)
public class AllInOneDomain {
    @ProtoField
    private StringDomain strVal;

    @ProtoField
    private BytesDomain bytesVal;

    @ProtoField
    private ConcreteMapToMessageDomain mapVal;

    @ProtoField
    private MessageListDomain listVal;

    @OneofBase(oneOfFields = {
            @OneofField(protoField = "oneof1_int_val", domainClass = OneofIntImplDomain.class, domainField = "intVal"),
            @OneofField(protoField = "oneof1_primitives", domainClass = PrimitiveDomain.class)
    })
    private OneofBaseFieldDomain value1;

    @OneofBase(oneOfFields = {
            @OneofField(protoField = "oneof2_int_val", domainClass = OneofIntImplDomain.class, domainField = "intVal"),
            @OneofField(protoField = "oneof2_primitives", domainClass = PrimitiveDomain.class)
    })
    private OneofBaseFieldDomain value2;
}