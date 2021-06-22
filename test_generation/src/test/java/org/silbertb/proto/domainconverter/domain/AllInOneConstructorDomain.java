package org.silbertb.proto.domainconverter.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.silbertb.proto.domainconverter.annotations.*;
import org.silbertb.proto.domainconverter.domain.oneof.field.OneofBaseFieldDomain;
import org.silbertb.proto.domainconverter.domain.oneof.field.OneofIntImplDomain;
import org.silbertb.proto.domainconverter.test.proto.AllInOneConstructorProto;

@ToString
@EqualsAndHashCode
@ProtoClass(protoClass = AllInOneConstructorProto.class)
public class AllInOneConstructorDomain {

    @ProtoConstructor
    public AllInOneConstructorDomain(
            @ProtoField StringDomain strVal,
            @ProtoField BytesDomain bytesVal,
            @ProtoField ConcreteMapToMessageDomain mapVal,
            @ProtoField MessageListDomain listVal,
            @OneofBase(oneOfFields = {
                    @OneofField(protoField = "oneof1_int_val", domainClass = OneofIntImplDomain.class, domainField = "intVal"),
                    @OneofField(protoField = "oneof1_primitives", domainClass = PrimitiveDomain.class)
            })
            OneofBaseFieldDomain value1,
            @OneofBase(oneOfFields = {
                    @OneofField(protoField = "oneof2_int_val", domainClass = OneofIntImplDomain.class, domainField = "intVal"),
                    @OneofField(protoField = "oneof2_primitives", domainClass = PrimitiveDomain.class)
            })
            OneofBaseFieldDomain value2) {
        this.strVal = strVal;
        this.bytesVal = bytesVal;
        this.mapVal = mapVal;
        this.listVal = listVal;
        this.value1 = value1;
        this.value2 = value2;
    }

    @Getter final private StringDomain strVal;
    @Getter final private BytesDomain bytesVal;
    @Getter final private ConcreteMapToMessageDomain mapVal;
    @Getter final private MessageListDomain listVal;
    @Getter final private OneofBaseFieldDomain value1;
    @Getter final private OneofBaseFieldDomain value2;
}
