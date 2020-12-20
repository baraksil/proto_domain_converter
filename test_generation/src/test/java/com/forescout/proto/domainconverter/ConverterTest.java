package com.forescout.proto.domainconverter;

//import com.forescout.example.annotation.processing.HelloWorld;
import com.forescout.proto.domainconverter.domain.PrimitiveDomain;
import com.forescout.proto.domainconverter.generated.ProtoDomainConverter;
import com.forescout.proto.domainconverter.test.proto.PrimitiveTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {

    @Test
    void testPrimitivesToProto() {
        PrimitiveDomain domain = createPrimitiveDomain();
        PrimitiveTest proto = ProtoDomainConverter.toProto(domain);
        PrimitiveTest expected = createPrimitiveTest();

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

    private PrimitiveTest createPrimitiveTest() {
        return PrimitiveTest.newBuilder()
                .setBooleanValue(true)
                .setFloatValue(-0.1f)
                .setDoubleValue(-0.5)
                .setIntValue(-1)
                .setLongValue(-2L)
                .build();
    }
}
