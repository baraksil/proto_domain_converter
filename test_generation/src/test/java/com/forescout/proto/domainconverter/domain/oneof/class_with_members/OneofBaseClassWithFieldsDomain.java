package com.forescout.proto.domainconverter.domain.oneof.class_with_members;

import com.forescout.proto.domainconverter.annotations.OneofBase;
import com.forescout.proto.domainconverter.annotations.OneofField;
import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.OneofBaseClassWithFieldsProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = OneofBaseClassWithFieldsProto.class)
@OneofBase(oneofName = "value", oneOfFields = {
        @OneofField(protoField = "long_val", domainClass = OneofLongImplDomain.class, domainField = "longVal"),
        @OneofField(protoField = "double_val", domainClass = OneofDoubleImplDomain.class, domainField = "doubleVal")
})
public class OneofBaseClassWithFieldsDomain {

    @ProtoField
    private String stringVal;
}
