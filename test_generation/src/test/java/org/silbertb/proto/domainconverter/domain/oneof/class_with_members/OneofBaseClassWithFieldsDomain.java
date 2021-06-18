package org.silbertb.proto.domainconverter.domain.oneof.class_with_members;

import org.silbertb.proto.domainconverter.annotations.OneofBase;
import org.silbertb.proto.domainconverter.annotations.OneofField;
import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.OneofBaseClassWithFieldsProto;
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
