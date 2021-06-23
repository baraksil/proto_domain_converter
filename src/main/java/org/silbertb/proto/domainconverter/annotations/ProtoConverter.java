package org.silbertb.proto.domainconverter.annotations;

import org.silbertb.proto.domainconverter.custom.ProtoType;
import org.silbertb.proto.domainconverter.custom.TypeConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
public @interface ProtoConverter {
    Class<? extends TypeConverter<?, ?>> converter();
    ProtoType protoType() default ProtoType.OTHER;
}
