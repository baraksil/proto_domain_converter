package org.silbertb.proto.domainconverter.domain.oneof.clazz;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.TwoIntsProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = TwoIntsProto.class)
public class TwoIntsDomain implements OneofBaseClassDomain {

    @ProtoField
    private int intVal1;

    @ProtoField
    private int intVal2;

}
