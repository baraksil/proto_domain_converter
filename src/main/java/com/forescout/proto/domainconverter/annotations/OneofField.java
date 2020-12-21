package com.forescout.proto.domainconverter.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface OneofField {
    String protoField();
    Class<?> domainClass();
    String domainField() default "";
}
