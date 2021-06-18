package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.OneofBase;
import org.silbertb.proto.domainconverter.annotations.OneofField;
import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.domain.oneof.field.OneofBaseFieldDomain;
import org.silbertb.proto.domainconverter.domain.oneof.field.OneofIntImplDomain;
import org.silbertb.proto.domainconverter.test.proto.AllInOneProto;
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