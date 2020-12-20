package com.forescout.proto.domainconverter;

import com.forescout.proto.domainconverter.domain.PrimitiveDomain;
import com.forescout.proto.domainconverter.domain.SimpleContainerDomain;
import com.forescout.proto.domainconverter.generated.ProtoDomainConverter;
import com.forescout.proto.domainconverter.test.proto.PrimitivesProto;
import com.forescout.proto.domainconverter.test.proto.SimpleContainerProto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {

    @Test
    void testPrimitivesToProto() {
        PrimitiveDomain domain = createPrimitiveDomain();
        PrimitivesProto proto = ProtoDomainConverter.toProto(domain);
        PrimitivesProto expected = createPrimitivesProto();

        assertEquals(expected, proto);
    }

    @Test
    void testSimpleContainerToProto() {
        SimpleContainerDomain domain = createSimpleContainerDomain();
        SimpleContainerProto proto = ProtoDomainConverter.toProto(domain);
        SimpleContainerProto expected = createSimpleContainerProto();

        assertEquals(expected, proto);
    }

    private PrimitiveDomain createPrimitiveDomain() {
        PrimitiveDomain primitiveDomain = new PrimitiveDomain();
        primitiveDomain.setBooleanValue(true);
        primitiveDomain.setFloatValue(-0.1f);
        primitiveDomain.setDoubleValue(-0.5);
        primitiveDomain.setIntValue(-1);
        primitiveDomain.setLongValue(-2L);

        return primitiveDomain;
    }

    private PrimitivesProto createPrimitivesProto() {
        return PrimitivesProto.newBuilder()
                .setBooleanValue(true)
                .setFloatValue(-0.1f)
                .setDoubleValue(-0.5)
                .setIntValue(-1)
                .setLongValue(-2L)
                .build();
    }

    private SimpleContainerDomain createSimpleContainerDomain() {
        SimpleContainerDomain simpleContainerDomain =  new SimpleContainerDomain();
        simpleContainerDomain.setStrValue("yabadabadoo");
        simpleContainerDomain.setPrimitives(createPrimitiveDomain());

        return simpleContainerDomain;
    }

    private SimpleContainerProto createSimpleContainerProto() {
        return SimpleContainerProto.newBuilder()
                .setStrValue("yabadabadoo")
                .setPrimitives(createPrimitivesProto())
                .build();
    }
}
