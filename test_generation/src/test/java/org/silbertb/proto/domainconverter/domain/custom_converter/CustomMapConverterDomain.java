package org.silbertb.proto.domainconverter.domain.custom_converter;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoConverter;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.custom.ProtoType;
import org.silbertb.proto.domainconverter.test.proto.CustomMapConverterProto;
import lombok.Data;

import java.util.HashMap;

@Data
@ProtoClass(protoClass = CustomMapConverterProto.class)
public class CustomMapConverterDomain {

    @ProtoField(protoName = "int_map")
    @ProtoConverter(converter = IntMapStringMapConverter.class, protoType = ProtoType.MAP)
    private HashMap<String, String> map;
}
