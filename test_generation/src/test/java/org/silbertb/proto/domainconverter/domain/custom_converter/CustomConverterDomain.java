package org.silbertb.proto.domainconverter.domain.custom_converter;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoConverter;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.CustomConverterProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = CustomConverterProto.class)
public class CustomConverterDomain {
    @ProtoField(protoName = "int_val")
    @ProtoConverter(converter = IntStrConverter.class)
    private String strVal;
}
