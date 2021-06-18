package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.OneofWithoutInheritanceProto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString @EqualsAndHashCode
@ProtoClass(protoClass = OneofWithoutInheritanceProto.class)
public class OneofWithoutInheritanceDomain {

    @ProtoField
    private int intVal;

    @ProtoField
    private PrimitiveDomain primitives;

    public void setIntVal(int intVal) {
        this.intVal = intVal;
        this.primitives = null;
    }

    public void setPrimitives(PrimitiveDomain primitives) {
        this.primitives = primitives;
        this.intVal = 0;
    }
}
