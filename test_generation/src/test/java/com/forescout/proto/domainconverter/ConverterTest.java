package com.forescout.proto.domainconverter;

import com.forescout.proto.domainconverter.domain.*;
import com.forescout.proto.domainconverter.domain.oneof.OneofIntImplDomain;
import com.forescout.proto.domainconverter.domain.oneof.OneofWithInheritanceDomain;
import com.forescout.proto.domainconverter.generated.ProtoDomainConverter;
import com.forescout.proto.domainconverter.test.proto.*;
import com.google.protobuf.ByteString;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConverterTest {

    @Test
    void testPrimitivesToProto() {
        PrimitiveDomain domain = TestItemsCreator.createPrimitiveDomain();
        PrimitivesProto proto = ProtoDomainConverter.toProto(domain);
        PrimitivesProto expected = TestItemsCreator.createPrimitivesProto();

        assertEquals(expected, proto);
    }

    @Test
    void testPrimitivesToDomain() {
        PrimitivesProto proto = TestItemsCreator.createPrimitivesProto();
        PrimitiveDomain domain = ProtoDomainConverter.toDomain(proto);
        PrimitiveDomain expected = TestItemsCreator.createPrimitiveDomain();

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
        StringDomain domain = TestItemsCreator.createStringDomain();
        StringProto proto = ProtoDomainConverter.toProto(domain);
        StringProto expected = TestItemsCreator.createStringProto();

        assertEquals(expected, proto);
    }

    @Test
    void testStringToDomain() {
        StringProto proto = TestItemsCreator.createStringProto();
        StringDomain domain = ProtoDomainConverter.toDomain(proto);
        StringDomain expected = TestItemsCreator.createStringDomain();

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
    void testBytesToProto() {
        BytesDomain domain = TestItemsCreator.createBytesDomain();
        BytesProto proto = ProtoDomainConverter.toProto(domain);
        BytesProto expected = TestItemsCreator.createBytesProto();

        assertEquals(expected, proto);
    }

    @Test
    void testBytesToDomain() {
        BytesProto proto = TestItemsCreator.createBytesProto();
        BytesDomain domain = ProtoDomainConverter.toDomain(proto);
        BytesDomain expected = TestItemsCreator.createBytesDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testEmptyBytesToProto() {
        BytesDomain domain = new BytesDomain();
        BytesProto proto = ProtoDomainConverter.toProto(domain);
        BytesProto expected = BytesProto.newBuilder().build();

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
        SimpleContainerDomain domain = TestItemsCreator.createSimpleContainerDomain();
        SimpleContainerProto proto = ProtoDomainConverter.toProto(domain);
        SimpleContainerProto expected = TestItemsCreator.createSimpleContainerProto();

        assertEquals(expected, proto);
    }

    @Test
    void testSimpleContainerToDomain() {
        SimpleContainerProto proto = TestItemsCreator.createSimpleContainerProto();
        SimpleContainerDomain domain = ProtoDomainConverter.toDomain(proto);
        SimpleContainerDomain expected = TestItemsCreator.createSimpleContainerDomain();

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
        PrimitiveListDomain domain = TestItemsCreator.createPrimitiveListDomain();
        PrimitiveListProto proto = ProtoDomainConverter.toProto(domain);
        PrimitiveListProto expected = TestItemsCreator.createPrimitiveListProto();

        assertEquals(expected, proto);
    }

    @Test
    void testPrimitiveListToDomain() {
        PrimitiveListProto proto = TestItemsCreator.createPrimitiveListProto();
        PrimitiveListDomain domain = ProtoDomainConverter.toDomain(proto);
        PrimitiveListDomain expected = TestItemsCreator.createPrimitiveListDomain();

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
        ConcretePrimitiveListProto proto = TestItemsCreator.createConcretePrimitiveListProto();
        ConcretePrimitiveListDomain domain = ProtoDomainConverter.toDomain(proto);
        ConcretePrimitiveListDomain expected = TestItemsCreator.createConcretePrimitiveListDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testStringListToProto() {
        StringListDomain domain = TestItemsCreator.createStringListDomain();
        StringListProto proto = ProtoDomainConverter.toProto(domain);
        StringListProto expected = TestItemsCreator.createStringListProto();

        assertEquals(expected, proto);
    }

    @Test
    void testStringListToDomain() {
        StringListProto proto = TestItemsCreator.createStringListProto();
        StringListDomain domain = ProtoDomainConverter.toDomain(proto);
        StringListDomain expected = TestItemsCreator.createStringListDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testMessageListToProto() {
        MessageListDomain domain = TestItemsCreator.createMessageListDomain();
        MessageListProto proto = ProtoDomainConverter.toProto(domain);
        MessageListProto expected = TestItemsCreator.createMessageListProto();

        assertEquals(expected, proto);
    }

    @Test
    void testMessageListToDomain() {
        MessageListProto proto = TestItemsCreator.createMessageListProto();
        MessageListDomain domain = ProtoDomainConverter.toDomain(proto);
        MessageListDomain expected = TestItemsCreator.createMessageListDomain();

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
        ConcreteMessageListProto proto = TestItemsCreator.createConcreteMessageListProto();
        ConcreteMessageListDomain domain = ProtoDomainConverter.toDomain(proto);
        ConcreteMessageListDomain expected = TestItemsCreator.createConcreteMessageListDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testPrimitiveMapToProto() {
        PrimitiveMapDomain domain = TestItemsCreator.createPrimitiveMapDomain();
        PrimitiveMapProto proto = ProtoDomainConverter.toProto(domain);
        PrimitiveMapProto expected = TestItemsCreator.createPrimitiveMapProto();

        assertEquals(expected, proto);
    }

    @Test
    void testPrimitiveMapToDomain() {
        PrimitiveMapProto proto = TestItemsCreator.createPrimitiveMapProto();
        PrimitiveMapDomain domain = ProtoDomainConverter.toDomain(proto);
        PrimitiveMapDomain expected = TestItemsCreator.createPrimitiveMapDomain();

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
        ConcretePrimitiveMapProto proto = TestItemsCreator.createConcretePrimitiveMapProto();
        ConcretePrimitiveMapDomain domain = ProtoDomainConverter.toDomain(proto);
        ConcretePrimitiveMapDomain expected = TestItemsCreator.createConcretePrimitiveMapDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testMapToMessageToProto() {
        MapToMessageDomain domain = TestItemsCreator.createMapToMessageDomain();
        MapToMessageProto proto = ProtoDomainConverter.toProto(domain);
        MapToMessageProto expected = TestItemsCreator.createMapToMessageProto();

        assertEquals(expected, proto);
    }

    @Test
    void testConcreteMapToMessageToDomain() {
        ConcreteMapToMessageProto proto = TestItemsCreator.createConcreteMapToMessageProto();
        ConcreteMapToMessageDomain domain = ProtoDomainConverter.toDomain(proto);
        ConcreteMapToMessageDomain expected = TestItemsCreator.createConcreteMapToMessageDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testOneofWithoutInheritanceToProto() {
        OneofWithoutInheritanceDomain domain = TestItemsCreator.createOneofWithoutInheritanceDomain();
        OneofWithoutInheritanceProto proto = ProtoDomainConverter.toProto(domain);
        OneofWithoutInheritanceProto expected = TestItemsCreator.createOneofWithoutInheritanceProto();

        assertEquals(expected, proto);
    }

    @Test
    void testOneofWithoutInheritanceToDomain() {
        OneofWithoutInheritanceProto proto = TestItemsCreator.createOneofWithoutInheritanceProto();
        OneofWithoutInheritanceDomain domain = ProtoDomainConverter.toDomain(proto);
        OneofWithoutInheritanceDomain expected = TestItemsCreator.createOneofWithoutInheritanceDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testOneofWithInheritanceToProto() {
        OneofWithInheritanceDomain domain = TestItemsCreator.createOneofWithInheritanceDomain();
        OneofWithInheritanceProto proto = ProtoDomainConverter.toProto(domain);
        OneofWithInheritanceProto expected = TestItemsCreator.createOneofWithInheritanceProto();

        assertEquals(expected, proto);
    }

    @Test
    void testOneofWithInheritanceToDomain() {
        OneofWithInheritanceProto proto = TestItemsCreator.createOneofWithInheritanceProto();
        OneofWithInheritanceDomain domain = ProtoDomainConverter.toDomain(proto);
        OneofWithInheritanceDomain expected = TestItemsCreator.createOneofWithInheritanceDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testAllInOneToProto() {
        AllInOneDomain domain = TestItemsCreator.createAllInOneDomain();
        AllInOneProto proto = ProtoDomainConverter.toProto(domain);
        AllInOneProto expected = TestItemsCreator.createAllInOneProto();

        assertEquals(expected, proto);
    }

}
