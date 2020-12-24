package com.forescout.proto.domainconverter.domain.custom_converter;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoConverter;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.custom.ProtoType;
import com.forescout.proto.domainconverter.test.proto.CustomListConverterProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = CustomListConverterProto.class)
public class CustomListConverterDomain {
    @ProtoField(protoName = "int_list")
    @ProtoConverter(converter = IntListToCommaSeparatedStringConverter.class, protoType = ProtoType.LIST)
    private String commaSeparatedInt;
}
