package org.silbertb.proto.domainconverter.annotations;

import org.silbertb.proto.domainconverter.custom.ProtoType;
import org.silbertb.proto.domainconverter.custom.TypeConverter;

public @interface ProtoConverter {
    Class<? extends TypeConverter<?, ?>> converter();
    ProtoType protoType() default ProtoType.OTHER;
}
