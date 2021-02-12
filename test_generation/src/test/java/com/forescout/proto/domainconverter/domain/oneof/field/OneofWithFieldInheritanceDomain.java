package com.forescout.proto.domainconverter.domain.oneof.field;

import com.forescout.proto.domainconverter.annotations.OneofBase;
import com.forescout.proto.domainconverter.annotations.OneofField;
import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.domain.PrimitiveDomain;
import com.forescout.proto.domainconverter.test.proto.OneofWithFieldInheritanceProto;
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
