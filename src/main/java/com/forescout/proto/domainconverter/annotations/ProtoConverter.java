package com.forescout.proto.domainconverter.annotations;

import com.forescout.proto.domainconverter.custom.ProtoType;
import com.forescout.proto.domainconverter.custom.TypeConverter;

public @interface ProtoConverter {
    Class<? extends TypeConverter<?, ?>> converter();
    ProtoType protoType() default ProtoType.OTHER;
}
