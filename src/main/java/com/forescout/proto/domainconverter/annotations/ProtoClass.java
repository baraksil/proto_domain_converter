package com.forescout.proto.domainconverter.annotations;

import com.google.protobuf.Message;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ProtoClass {
    Class<? extends Message> protoClass();
    boolean withInheritedFields() default false;
}
