package com.forescout.proto.domainconverter.domain.oneof.clazz;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.TwoIntsProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = TwoIntsProto.class)
public class TwoIntsDomain implements OneofBaseClassDomain {

    @ProtoField
    private int intVal1;

    @ProtoField
    private int intVal2;

}
