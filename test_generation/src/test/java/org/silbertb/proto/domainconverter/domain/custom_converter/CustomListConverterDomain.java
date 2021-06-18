package org.silbertb.proto.domainconverter.domain.custom_converter;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoConverter;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.custom.ProtoType;
import org.silbertb.proto.domainconverter.test.proto.CustomListConverterProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = CustomListConverterProto.class)
public class CustomListConverterDomain {
    @ProtoField(protoName = "int_list")
    @ProtoConverter(converter = IntListToCommaSeparatedStringConverter.class, protoType = ProtoType.LIST)
    private String commaSeparatedInt;
}
