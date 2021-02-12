package com.forescout.proto.domainconverter.domain.oneof.clazz;


import com.forescout.proto.domainconverter.annotations.OneofBase;
import com.forescout.proto.domainconverter.annotations.OneofField;
import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.test.proto.OneofBaseClassProto;

@OneofBase(oneofName = "value", oneOfFields = {
        @OneofField(protoField = "string_val", domainClass = OneofStringImplDomain.class, domainField = "stringVal"),
        @OneofField(protoField = "two_ints_val", domainClass = TwoIntsDomain.class)
})
@ProtoClass(protoClass = OneofBaseClassProto.class)
public interface OneofBaseClassDomain {
}
