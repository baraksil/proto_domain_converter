package com.forescout.proto.domainconverter;

import com.forescout.proto.domainconverter.domain.MessageListDomain;
import com.forescout.proto.domainconverter.domain.PrimitiveListDomain;
import com.forescout.proto.domainconverter.domain.PrimitiveDomain;
import com.forescout.proto.domainconverter.domain.SimpleContainerDomain;
import com.forescout.proto.domainconverter.generated.ProtoDomainConverter;
import com.forescout.proto.domainconverter.test.proto.MessageListProto;
import com.forescout.proto.domainconverter.test.proto.PrimitiveListProto;
import com.forescout.proto.domainconverter.test.proto.PrimitivesProto;
import com.forescout.proto.domainconverter.test.proto.SimpleContainerProto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    void testPrimitiveListToProto() {
        PrimitiveListDomain domain = createPrimitiveListDomain();
        PrimitiveListProto proto = ProtoDomainConverter.toProto(domain);
        PrimitiveListProto expected = createPrimitiveListProto();

        assertEquals(expected, proto);
    }

    @Test
    void testMessageListToProto() {
        MessageListDomain domain = createMessageListDomain();
        MessageListProto proto = ProtoDomainConverter.toProto(domain);
        MessageListProto expected = createMessageListProto();

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

    private PrimitiveListDomain createPrimitiveListDomain() {
        PrimitiveListDomain listDomain = new PrimitiveListDomain();
        listDomain.setListInt(new ArrayList<>(List.of(1, 2, 3)));
        return listDomain;
    }

    private PrimitiveListProto createPrimitiveListProto() {
        return PrimitiveListProto.newBuilder().addAllListInt(List.of(1, 2, 3)).build();
    }

    private MessageListDomain createMessageListDomain() {
        MessageListDomain listDomain = new MessageListDomain();
        listDomain.setMessageList(new ArrayList<>(List.of(createPrimitiveDomain(), createPrimitiveDomain())));
        return listDomain;
    }

    private MessageListProto createMessageListProto() {
        return MessageListProto.newBuilder()
                .addMessageList(createPrimitivesProto())
                .addMessageList(createPrimitivesProto())
                .build();
    }
}
