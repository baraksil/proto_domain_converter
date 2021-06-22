package org.silbertb.proto.domainconverter;

import org.silbertb.proto.domainconverter.domain.*;
import org.silbertb.proto.domainconverter.domain.custom_converter.CustomListConverterDomain;
import org.silbertb.proto.domainconverter.domain.custom_converter.CustomMapConverterDomain;
import org.silbertb.proto.domainconverter.domain.custom_mapper.CustomMapperDomain;
import org.silbertb.proto.domainconverter.domain.oneof.class_with_members.OneofBaseClassWithFieldsDomain;
import org.silbertb.proto.domainconverter.domain.oneof.field.OneofWithFieldInheritanceDomain;
import org.silbertb.proto.domainconverter.domain.custom_converter.CustomConverterDomain;
import org.silbertb.proto.domainconverter.domain.oneof.clazz.OneofBaseClassDomain;
import org.silbertb.proto.domainconverter.generated.ProtoDomainConverter;
import org.silbertb.proto.domainconverter.test.proto.*;
import org.junit.jupiter.api.Test;
import org.silbertb.proto.domainconverter.domain.*;

import java.util.Collections;

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
        OneofWithFieldInheritanceDomain domain = TestItemsCreator.createOneofWithInheritanceDomain();
        OneofWithFieldInheritanceProto proto = ProtoDomainConverter.toProto(domain);
        OneofWithFieldInheritanceProto expected = TestItemsCreator.createOneofWithFieldInheritanceProto();

        assertEquals(expected, proto);
    }

    @Test
    void testOneofWithInheritanceToDomain() {
        OneofWithFieldInheritanceProto proto = TestItemsCreator.createOneofWithFieldInheritanceProto();
        OneofWithFieldInheritanceDomain domain = ProtoDomainConverter.toDomain(proto);
        OneofWithFieldInheritanceDomain expected = TestItemsCreator.createOneofWithInheritanceDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testOneofBaseClassToProto() {
        OneofBaseClassDomain domain = TestItemsCreator.createOneofbaseClassDomain();
        OneofBaseClassProto proto = ProtoDomainConverter.toProto(domain);
        OneofBaseClassProto expected = TestItemsCreator.createOneofBaseClassProto();

        assertEquals(expected, proto);
    }

    @Test
    void testOneofBaseClassToDomain() {
        OneofBaseClassProto proto = TestItemsCreator.createOneofBaseClassProto();
        OneofBaseClassDomain domain = ProtoDomainConverter.toDomain(proto);
        OneofBaseClassDomain expected = TestItemsCreator.createOneofbaseClassDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testOneofBaseClassWithFieldsToProto() {
        OneofBaseClassWithFieldsDomain domain = TestItemsCreator.createOneofbaseClassWithFieldsDomain();
        OneofBaseClassWithFieldsProto proto = ProtoDomainConverter.toProto(domain);
        OneofBaseClassWithFieldsProto expected = TestItemsCreator.OneofBaseClassWithFieldsProto();

        assertEquals(expected, proto);
    }

    @Test
    void testOneofBaseClassWithFieldsToDomain() {
        OneofBaseClassWithFieldsProto proto = TestItemsCreator.OneofBaseClassWithFieldsProto();
        OneofBaseClassWithFieldsDomain domain = ProtoDomainConverter.toDomain(proto);
        OneofBaseClassWithFieldsDomain expected = TestItemsCreator.createOneofbaseClassWithFieldsDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testAllInOneToProto() {
        AllInOneDomain domain = TestItemsCreator.createAllInOneDomain();
        AllInOneProto proto = ProtoDomainConverter.toProto(domain);
        AllInOneProto expected = TestItemsCreator.createAllInOneProto();

        assertEquals(expected, proto);
    }

    @Test
    void testAllInOneToDomain() {
        AllInOneProto proto = TestItemsCreator.createAllInOneProto();
        AllInOneDomain domain = ProtoDomainConverter.toDomain(proto);
        AllInOneDomain expected = TestItemsCreator.createAllInOneDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testCustomConverterToDomain() {
        CustomConverterProto proto = TestItemsCreator.createCustomConverterProto();
        CustomConverterDomain domain = ProtoDomainConverter.toDomain(proto);
        CustomConverterDomain expected = TestItemsCreator.createCustomConverterDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testCustomConverterToProto() {
        CustomConverterDomain domain = TestItemsCreator.createCustomConverterDomain();
        CustomConverterProto proto = ProtoDomainConverter.toProto(domain);
        CustomConverterProto expected = TestItemsCreator.createCustomConverterProto();

        assertEquals(expected, proto);
    }

    @Test
    void testCustomListConverterToDomain() {
        CustomListConverterProto proto = TestItemsCreator.createCustomListConverterProto();
        CustomListConverterDomain domain = ProtoDomainConverter.toDomain(proto);
        CustomListConverterDomain expected = TestItemsCreator.createCustomListConverterDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testCustomListConverterToProto() {
        CustomListConverterDomain domain = TestItemsCreator.createCustomListConverterDomain();
        CustomListConverterProto proto = ProtoDomainConverter.toProto(domain);
        CustomListConverterProto expected = TestItemsCreator.createCustomListConverterProto();

        assertEquals(expected, proto);
    }

    @Test
    void testCustomMapConverterToDomain() {
        CustomMapConverterProto proto = TestItemsCreator.createCustomMapConverterProto();
        CustomMapConverterDomain domain = ProtoDomainConverter.toDomain(proto);
        CustomMapConverterDomain expected = TestItemsCreator.createCustomMapConverterDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testCustomMapConverterToProto() {
        CustomMapConverterDomain domain = TestItemsCreator.createCustomMapConverterDomain();
        CustomMapConverterProto proto = ProtoDomainConverter.toProto(domain);
        CustomMapConverterProto expected = TestItemsCreator.createCustomMapConverterProto();

        assertEquals(expected, proto);
    }

    @Test
    void testCustomMapperToDomain() {
        CustomMapperProto proto = TestItemsCreator.createCustomMapperProto();
        CustomMapperDomain domain = ProtoDomainConverter.toDomain(proto);
        CustomMapperDomain expected = TestItemsCreator.createCustomMapperDomain();

        assertEquals(expected, domain);
    }

    @Test
    void testCustomMapperToProto() {
        CustomMapperDomain domain = TestItemsCreator.createCustomMapperDomain();
        CustomMapperProto proto = ProtoDomainConverter.toProto(domain);
        CustomMapperProto expected = TestItemsCreator.createCustomMapperProto();

        assertEquals(expected, proto);
    }

    @Test
    void testAllInOneConstructorToProto() {
        AllInOneConstructorDomain domain = TestItemsCreator.createAllInOneConstructorDomain();
        AllInOneConstructorProto proto = ProtoDomainConverter.toProto(domain);
        AllInOneConstructorProto expected = TestItemsCreator.createAllInOneConstructorProto();

        assertEquals(expected, proto);
    }

    @Test
    void testAllInOneConstructorToDomain() {
        AllInOneConstructorProto proto = TestItemsCreator.createAllInOneConstructorProto();
        AllInOneConstructorDomain domain = ProtoDomainConverter.toDomain(proto);
        AllInOneConstructorDomain expected = TestItemsCreator.createAllInOneConstructorDomain();

        assertEquals(expected, domain);
    }

}
