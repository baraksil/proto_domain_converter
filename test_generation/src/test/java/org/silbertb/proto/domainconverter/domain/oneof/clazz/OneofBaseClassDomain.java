package org.silbertb.proto.domainconverter.domain.oneof.clazz;


import org.silbertb.proto.domainconverter.annotations.OneofBase;
import org.silbertb.proto.domainconverter.annotations.OneofField;
import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.test.proto.OneofBaseClassProto;

@OneofBase(oneofName = "value", oneOfFields = {
        @OneofField(protoField = "string_val", domainClass = OneofStringImplDomain.class, domainField = "stringVal"),
        @OneofField(protoField = "two_ints_val", domainClass = TwoIntsDomain.class)
})
@ProtoClass(protoClass = OneofBaseClassProto.class)
public interface OneofBaseClassDomain {
}
