package com.forescout.proto.domainconverter.domain.custom_converter;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoConverter;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.custom.ProtoType;
import com.forescout.proto.domainconverter.test.proto.CustomMapConverterProto;
import com.google.protobuf.Internal;
import lombok.Data;

import java.util.HashMap;

@Data
@ProtoClass(protoClass = CustomMapConverterProto.class)
public class CustomMapConverterDomain {

    @ProtoField(protoName = "int_map")
    @ProtoConverter(converter = IntMapStringMapConverter.class, protoType = ProtoType.MAP)
    private HashMap<String, String> map;
}
