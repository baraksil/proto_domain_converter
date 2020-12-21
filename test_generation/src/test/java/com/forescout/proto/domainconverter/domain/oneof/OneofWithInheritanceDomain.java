package com.forescout.proto.domainconverter.domain.oneof;

import com.forescout.proto.domainconverter.annotations.OneofBase;
import com.forescout.proto.domainconverter.annotations.OneofField;
import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.domain.PrimitiveDomain;
import com.forescout.proto.domainconverter.test.proto.OneofWithInheritanceProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = OneofWithInheritanceProto.class)
public class OneofWithInheritanceDomain {

    @OneofBase(oneOfFields = {
            @OneofField(protoField = "int_val", domainClass = OneofIntImplDomain.class, domainField = "intVal"),
            @OneofField(protoField = "primitives", domainClass = PrimitiveDomain.class)
    })
    private OneofBaseDomain value;
}
