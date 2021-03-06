package org.silbertb.proto.domainconverter.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
public @interface ProtoField {
    String protoName() default "";
}
