package com.forescout.proto.domainconverter;

import com.forescout.proto.domainconverter.domain.*;
import com.forescout.proto.domainconverter.domain.oneof.OneofIntImplDomain;
import com.forescout.proto.domainconverter.domain.oneof.OneofWithInheritanceDomain;
import com.forescout.proto.domainconverter.generated.ProtoDomainConverter;
import com.forescout.proto.domainconverter.test.proto.*;
import org.junit.jupiter.api.Test;

import java.util.*;

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
    void testPrimitivesToDomain() {
        PrimitivesProto proto = createPrimitivesProto();
        PrimitiveDomain domain = ProtoDomainConverter.toDomain(proto);
        PrimitiveDomain expected = createPrimitiveDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testEmptyPrimitivesToProto() {
        PrimitiveDomain domain = new PrimitiveDomain();
        PrimitivesProto proto = ProtoDomainConverter.toProto(domain);
        PrimitivesProto expected = PrimitivesProto.newBuilder().build();

        assertEquals(expected, proto);
    }

    @Test
    void testEmptyPrimitivesToDomain() {
        PrimitivesProto proto = PrimitivesProto.newBuilder().build();
        PrimitiveDomain domain = ProtoDomainConverter.toDomain(proto);
        PrimitiveDomain expected = new PrimitiveDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testStringToProto() {
        StringDomain domain = createStringDomain();
        StringProto proto = ProtoDomainConverter.toProto(domain);
        StringProto expected = createStringProto();

        assertEquals(expected, proto);
    }

    @Test
    void testStringToDomain() {
        StringProto proto = createStringProto();
        StringDomain domain = ProtoDomainConverter.toDomain(proto);
        StringDomain expected = createStringDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testEmptyStringToProto() {
        StringDomain domain = new StringDomain();
        StringProto proto = ProtoDomainConverter.toProto(domain);
        StringProto expected = StringProto.newBuilder().build();

        assertEquals(expected, proto);
    }

    @Test
    void testEmptyStringToDomain() {
        StringProto proto = StringProto.newBuilder().build();
        StringDomain domain = ProtoDomainConverter.toDomain(proto);
        StringDomain expected = new StringDomain();
        expected.setStringValue("");

        assertEquals(expected, domain);
    }

    @Test
    void testSimpleContainerToProto() {
        SimpleContainerDomain domain = createSimpleContainerDomain();
        SimpleContainerProto proto = ProtoDomainConverter.toProto(domain);
        SimpleContainerProto expected = createSimpleContainerProto();

        assertEquals(expected, proto);
    }

    @Test
    void testSimpleContainerToDomain() {
        SimpleContainerProto proto = createSimpleContainerProto();
        SimpleContainerDomain domain = ProtoDomainConverter.toDomain(proto);
        SimpleContainerDomain expected = createSimpleContainerDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testEmptySimpleContainerToProto() {
        SimpleContainerDomain domain = new SimpleContainerDomain();
        SimpleContainerProto proto = ProtoDomainConverter.toProto(domain);
        SimpleContainerProto expected = SimpleContainerProto.newBuilder().build();

        assertEquals(expected, proto);
    }

    @Test
    void testEmptySimpleContainerToDomain() {
        SimpleContainerProto proto = SimpleContainerProto.newBuilder().build();
        SimpleContainerDomain domain = ProtoDomainConverter.toDomain(proto);
        SimpleContainerDomain expected = new SimpleContainerDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testPrimitiveListToProto() {
        PrimitiveListDomain domain = createPrimitiveListDomain();
        PrimitiveListProto proto = ProtoDomainConverter.toProto(domain);
        PrimitiveListProto expected = createPrimitiveListProto();

        assertEquals(expected, proto);
    }

    @Test
    void testPrimitiveListToDomain() {
        PrimitiveListProto proto = createPrimitiveListProto();
        PrimitiveListDomain domain = ProtoDomainConverter.toDomain(proto);
        PrimitiveListDomain expected = createPrimitiveListDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testEmptyPrimitiveListToProto() {
        PrimitiveListDomain domain = new PrimitiveListDomain();
        PrimitiveListProto proto = ProtoDomainConverter.toProto(domain);
        PrimitiveListProto expected = PrimitiveListProto.newBuilder().build();

        assertEquals(expected, proto);
    }

    @Test
    void testEmptyPrimitiveListToDomain() {
        PrimitiveListProto proto = PrimitiveListProto.newBuilder().build();
        PrimitiveListDomain domain = ProtoDomainConverter.toDomain(proto);
        PrimitiveListDomain expected = new PrimitiveListDomain();
        expected.setIntList(Collections.emptyList());

        assertEquals(expected, domain);
    }

    @Test
    void testPrimitiveConcreteListToDomain() {
        ConcretePrimitiveListProto proto = createConcretePrimitiveListProto();
        ConcretePrimitiveListDomain domain = ProtoDomainConverter.toDomain(proto);
        ConcretePrimitiveListDomain expected = createConcretePrimitiveListDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testStringListToProto() {
        StringListDomain domain = createStringListDomain();
        StringListProto proto = ProtoDomainConverter.toProto(domain);
        StringListProto expected = createStringListProto();

        assertEquals(expected, proto);
    }

    @Test
    void testStringListToDomain() {
        StringListProto proto = createStringListProto();
        StringListDomain domain = ProtoDomainConverter.toDomain(proto);
        StringListDomain expected = createStringListDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testMessageListToProto() {
        MessageListDomain domain = createMessageListDomain();
        MessageListProto proto = ProtoDomainConverter.toProto(domain);
        MessageListProto expected = createMessageListProto();

        assertEquals(expected, proto);
    }

    @Test
    void testMessageListToDomain() {
        MessageListProto proto = createMessageListProto();
        MessageListDomain domain = ProtoDomainConverter.toDomain(proto);
        MessageListDomain expected = createMessageListDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testEmptyMessageListToProto() {
        MessageListDomain domain = new MessageListDomain();
        MessageListProto proto = ProtoDomainConverter.toProto(domain);
        MessageListProto expected = MessageListProto.newBuilder().build();

        assertEquals(expected, proto);
    }

    @Test
    void testConcreteMessageListToDomain() {
        ConcreteMessageListProto proto = createConcreteMessageListProto();
        ConcreteMessageListDomain domain = ProtoDomainConverter.toDomain(proto);
        ConcreteMessageListDomain expected = createConcreteMessageListDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testPrimitiveMapToProto() {
        PrimitiveMapDomain domain = createPrimitiveMapDomain();
        PrimitiveMapProto proto = ProtoDomainConverter.toProto(domain);
        PrimitiveMapProto expected = createPrimitiveMapProto();

        assertEquals(expected, proto);
    }

    @Test
    void testPrimitiveMapToDomain() {
        PrimitiveMapProto proto = createPrimitiveMapProto();
        PrimitiveMapDomain domain = ProtoDomainConverter.toDomain(proto);
        PrimitiveMapDomain expected = createPrimitiveMapDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testEmptyPrimitiveMapToProto() {
        PrimitiveMapDomain domain = new PrimitiveMapDomain();
        PrimitiveMapProto proto = ProtoDomainConverter.toProto(domain);
        PrimitiveMapProto expected = PrimitiveMapProto.newBuilder().build();

        assertEquals(expected, proto);
    }

    @Test
    void testEmptyPrimitiveMapToDomain() {
        PrimitiveMapProto proto = PrimitiveMapProto.newBuilder().build();
        PrimitiveMapDomain domain = ProtoDomainConverter.toDomain(proto);
        PrimitiveMapDomain expected = new PrimitiveMapDomain();
        expected.setPrimitiveMap(Collections.emptyMap());

        assertEquals(expected, domain);
    }

    @Test
    void testConcretePrimitiveMapToDomain() {
        ConcretePrimitiveMapProto proto = createConcretePrimitiveMapProto();
        ConcretePrimitiveMapDomain domain = ProtoDomainConverter.toDomain(proto);
        ConcretePrimitiveMapDomain expected = createConcretePrimitiveMapDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testMapToMessageToProto() {
        MapToMessageDomain domain = createMapToMessageDomain();
        MapToMessageProto proto = ProtoDomainConverter.toProto(domain);
        MapToMessageProto expected = createMapToMessageProto();

        assertEquals(expected, proto);
    }

    @Test
    void testConcreteMapToMessageToDomain() {
        ConcreteMapToMessageProto proto = createConcreteMapToMessageProto();
        ConcreteMapToMessageDomain domain = ProtoDomainConverter.toDomain(proto);
        ConcreteMapToMessageDomain expected = createConcreteMapToMessageDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testOneofWithoutInheritanceToProto() {
        OneofWithoutInheritanceDomain domain = createOneofWithoutInheritanceDomain();
        OneofWithoutInheritanceProto proto = ProtoDomainConverter.toProto(domain);
        OneofWithoutInheritanceProto expected = createOneofWithoutInheritanceProto();

        assertEquals(expected, proto);
    }

    @Test
    void testOneofWithoutInheritanceToDomain() {
        OneofWithoutInheritanceProto proto = createOneofWithoutInheritanceProto();
        OneofWithoutInheritanceDomain domain = ProtoDomainConverter.toDomain(proto);
        OneofWithoutInheritanceDomain expected = createOneofWithoutInheritanceDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testOneofWithInheritanceToProto() {
        OneofWithInheritanceDomain domain = createOneofWithInheritanceDomain();
        OneofWithInheritanceProto proto = ProtoDomainConverter.toProto(domain);
        OneofWithInheritanceProto expected = createOneofWithInheritanceProto();

        assertEquals(expected, proto);
    }

    @Test
    void testOneofWithInheritanceToDomain() {
        OneofWithInheritanceProto proto = createOneofWithInheritanceProto();
        OneofWithInheritanceDomain domain = ProtoDomainConverter.toDomain(proto);
        OneofWithInheritanceDomain expected = createOneofWithInheritanceDomain();

        assertEquals(expected, domain);
    }

    private OneofWithInheritanceDomain createOneofWithInheritanceDomain() {
        OneofWithInheritanceDomain domain = new OneofWithInheritanceDomain();
        OneofIntImplDomain oneofIntImplDomain = new OneofIntImplDomain();
        oneofIntImplDomain.setIntVal(3);
        domain.setValue(oneofIntImplDomain);
        return domain;
    }

    private OneofWithInheritanceProto createOneofWithInheritanceProto() {
        return OneofWithInheritanceProto.newBuilder()
                .setIntVal(3)
                .build();
    }

    private OneofWithoutInheritanceDomain createOneofWithoutInheritanceDomain() {
        OneofWithoutInheritanceDomain domain = new OneofWithoutInheritanceDomain();
        domain.setIntVal(3);
        return domain;
    }

    private OneofWithoutInheritanceProto createOneofWithoutInheritanceProto() {
        return OneofWithoutInheritanceProto.newBuilder()
                .setIntVal(3)
                .build();
    }

    private ConcreteMapToMessageDomain createConcreteMapToMessageDomain() {
        ConcreteMapToMessageDomain domain = new ConcreteMapToMessageDomain();
        domain.setMapToMessage(Map.of(
                "aa", createPrimitiveDomain(),
                "bb", createPrimitiveDomain()));
        return domain;
    }

    private ConcreteMapToMessageProto createConcreteMapToMessageProto() {
        return ConcreteMapToMessageProto.newBuilder()
                .putMapToMessage("aa", createPrimitivesProto())
                .putMapToMessage("bb", createPrimitivesProto())
                .build();
    }

    private ConcretePrimitiveMapDomain createConcretePrimitiveMapDomain() {
        ConcretePrimitiveMapDomain domain = new ConcretePrimitiveMapDomain();
        domain.setPrimitiveMap(Map.of(1, 2L, 3, 4L));
        return domain;
    }

    private ConcretePrimitiveMapProto createConcretePrimitiveMapProto() {
        return ConcretePrimitiveMapProto.newBuilder()
                .putPrimitiveMap(1, 2)
                .putPrimitiveMap(3, 4)
                .build();
    }

    private MapToMessageDomain createMapToMessageDomain() {
        MapToMessageDomain domain = new MapToMessageDomain();
        domain.setMapToMessage(Map.of(
                "aa", createPrimitiveDomain(),
                "bb", createPrimitiveDomain()));
        return domain;
    }

    private MapToMessageProto createMapToMessageProto() {
        return MapToMessageProto.newBuilder()
                .putMapToMessage("aa", createPrimitivesProto())
                .putMapToMessage("bb", createPrimitivesProto())
                .build();
    }

    private PrimitiveMapDomain createPrimitiveMapDomain() {
        PrimitiveMapDomain domain = new PrimitiveMapDomain();
        domain.setPrimitiveMap(Map.of(1, 2L, 3, 4L));
        return domain;
    }

    private PrimitiveMapProto createPrimitiveMapProto() {
        return PrimitiveMapProto.newBuilder()
                .putPrimitiveMap(1, 2)
                .putPrimitiveMap(3, 4)
                .build();
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

    private StringDomain createStringDomain() {
        StringDomain stringDomain = new StringDomain();
        stringDomain.setStringValue("aaaa");
        return stringDomain;
    }

    private StringProto createStringProto() {
        return StringProto.newBuilder().setStringValue("aaaa").build();
    }

    private SimpleContainerDomain createSimpleContainerDomain() {
        SimpleContainerDomain simpleContainerDomain =  new SimpleContainerDomain();
        simpleContainerDomain.setPrimitives(createPrimitiveDomain());

        return simpleContainerDomain;
    }

    private SimpleContainerProto createSimpleContainerProto() {
        return SimpleContainerProto.newBuilder()
                .setPrimitives(createPrimitivesProto())
                .build();
    }

    private PrimitiveListDomain createPrimitiveListDomain() {
        PrimitiveListDomain listDomain = new PrimitiveListDomain();
        listDomain.setIntList(new ArrayList<>(List.of(1, 2, 3)));
        return listDomain;
    }

    private PrimitiveListProto createPrimitiveListProto() {
        return PrimitiveListProto.newBuilder().addAllIntList(List.of(1, 2, 3)).build();
    }

    private ConcretePrimitiveListDomain createConcretePrimitiveListDomain() {
        ConcretePrimitiveListDomain listDomain = new ConcretePrimitiveListDomain();
        listDomain.setIntList(new LinkedList<>(List.of(1, 2, 3)));
        return listDomain;
    }

    private ConcretePrimitiveListProto createConcretePrimitiveListProto() {
        return ConcretePrimitiveListProto.newBuilder().addAllIntList(List.of(1, 2, 3)).build();
    }

    private StringListDomain createStringListDomain() {
        StringListDomain listDomain = new StringListDomain();
        listDomain.setStringList(new ArrayList<>(List.of("aa", "bb", "cc")));
        return listDomain;
    }

    private StringListProto createStringListProto() {
        return StringListProto.newBuilder().addAllStringList(List.of("aa", "bb", "cc")).build();
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

    private ConcreteMessageListDomain createConcreteMessageListDomain() {
        ConcreteMessageListDomain listDomain = new ConcreteMessageListDomain();
        listDomain.setMessageList(new LinkedList<>(List.of(createPrimitiveDomain(), createPrimitiveDomain())));
        return listDomain;
    }

    private ConcreteMessageListProto createConcreteMessageListProto() {
        return ConcreteMessageListProto.newBuilder()
                .addMessageList(createPrimitivesProto())
                .addMessageList(createPrimitivesProto())
                .build();
    }
}
