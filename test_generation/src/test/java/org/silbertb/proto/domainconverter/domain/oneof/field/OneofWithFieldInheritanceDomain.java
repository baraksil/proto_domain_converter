package org.silbertb.proto.domainconverter.domain.oneof.field;

import org.silbertb.proto.domainconverter.annotations.OneofBase;
import org.silbertb.proto.domainconverter.annotations.OneofField;
import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.domain.PrimitiveDomain;
import org.silbertb.proto.domainconverter.test.proto.OneofWithFieldInheritanceProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = OneofWithFieldInheritanceProto.class)
public class OneofWithFieldInheritanceDomain {

    @OneofBase(oneOfFields = {
            @OneofField(protoField = "int_val", domainClass = OneofIntImplDomain.class, domainField = "intVal"),
            @OneofField(protoField = "primitives", domainClass = PrimitiveDomain.class)
    })
    private OneofBaseFieldDomain value;
}
