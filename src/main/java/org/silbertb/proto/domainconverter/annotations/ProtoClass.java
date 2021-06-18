package org.silbertb.proto.domainconverter.annotations;

import org.silbertb.proto.domainconverter.custom.Mapper;
import org.silbertb.proto.domainconverter.custom.NullMapper;
import com.google.protobuf.Message;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ProtoClass {
    Class<? extends Message> protoClass();
    Class<? extends Mapper> mapper() default NullMapper.class;
    boolean withInheritedFields() default false;
}
