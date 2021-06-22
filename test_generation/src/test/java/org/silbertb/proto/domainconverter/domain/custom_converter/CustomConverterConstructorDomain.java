package org.silbertb.proto.domainconverter.domain.custom_converter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoConstructor;
import org.silbertb.proto.domainconverter.annotations.ProtoConverter;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.CustomConverterConstructorProto;

@ToString
@EqualsAndHashCode
@ProtoClass(protoClass = CustomConverterConstructorProto.class)
public class CustomConverterConstructorDomain {

    @ProtoConstructor
    public CustomConverterConstructorDomain(
            @ProtoField(protoName = "int_val")
            @ProtoConverter(converter = IntStrConverter.class)
            String strVal) {
        this.strVal = strVal;
    }

    @Getter final private String strVal;
}
