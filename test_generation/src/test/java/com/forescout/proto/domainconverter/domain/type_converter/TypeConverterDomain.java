package com.forescout.proto.domainconverter.domain.type_converter;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoConverter;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.custom.ProtoType;
import com.forescout.proto.domainconverter.test.proto.TypeConverterProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = TypeConverterProto.class)
public class TypeConverterDomain {
    @ProtoField(protoName = "int_val")
    @ProtoConverter(converter = IntStrConverter.class)
    private String strVal;
}
