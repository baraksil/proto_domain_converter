package com.forescout.proto.domainconverter.annotations;

import com.forescout.proto.domainconverter.custom.NullConverterImpl;
import com.forescout.proto.domainconverter.custom.TypeConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface ProtoField {
    String protoName() default "";
    /**
     * Retrieve class that perform field value conversion.
     *
     * @return Class for converting field value.
     */
    Class<? extends TypeConverter<?, ?>> converter() default NullConverterImpl.class;
}
