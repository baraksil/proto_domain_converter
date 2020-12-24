package com.forescout.proto.domainconverter.domain.custom_converter;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoConverter;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.CustomConverterProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = CustomConverterProto.class)
public class CustomConverterDomain {
    @ProtoField(protoName = "int_val")
    @ProtoConverter(converter = IntStrConverter.class)
    private String strVal;
}
