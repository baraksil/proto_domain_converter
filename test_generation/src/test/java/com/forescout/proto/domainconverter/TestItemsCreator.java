package com.forescout.proto.domainconverter;

import com.forescout.proto.domainconverter.domain.*;
import com.forescout.proto.domainconverter.domain.oneof.OneofIntImplDomain;
import com.forescout.proto.domainconverter.domain.oneof.OneofWithInheritanceDomain;
import com.forescout.proto.domainconverter.test.proto.*;
import com.google.protobuf.ByteString;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TestItemsCreator {
    static public AllInOneProto createAllInOneProto() {
        return AllInOneProto.newBuilder()
                .setBytesVal(createBytesProto())
                .setListVal(createMessageListProto())
                .setMapVal(createConcreteMapToMessageProto())
                .setOneof1IntVal(3)
                .setOneof2Primitives(createPrimitivesProto())
                .build();
    }

    static public AllInOneDomain createAllInOneDomain() {
        AllInOneDomain domain = new AllInOneDomain();
        domain.setBytesVal(createBytesDomain());
        domain.setListVal(createMessageListDomain());
        domain.setMapVal(createConcreteMapToMessageDomain());
        OneofIntImplDomain oneofIntImplDomain = new OneofIntImplDomain();
        oneofIntImplDomain.setIntVal(3);
        domain.setValue1(oneofIntImplDomain);
        domain.setValue2(createPrimitiveDomain());

        return domain;
    }

    static public BytesDomain createBytesDomain() {
        BytesDomain domain = new BytesDomain();
        domain.setBytesValue(new byte[]{0x1b, 0x2b});
        return domain;
    }

    static public BytesProto createBytesProto() {
        return BytesProto.newBuilder().setBytesValue(ByteString.copyFrom(new byte[]{0x1b, 0x2b})).build();
    }

    static public OneofWithInheritanceDomain createOneofWithInheritanceDomain() {
        OneofWithInheritanceDomain domain = new OneofWithInheritanceDomain();
        OneofIntImplDomain oneofIntImplDomain = new OneofIntImplDomain();
        oneofIntImplDomain.setIntVal(3);
        domain.setValue(oneofIntImplDomain);
        return domain;
    }

    static public OneofWithInheritanceProto createOneofWithInheritanceProto() {
        return OneofWithInheritanceProto.newBuilder()
                .setIntVal(3)
                .build();
    }

    static public OneofWithoutInheritanceDomain createOneofWithoutInheritanceDomain() {
        OneofWithoutInheritanceDomain domain = new OneofWithoutInheritanceDomain();
        domain.setIntVal(3);
        return domain;
    }

    static public OneofWithoutInheritanceProto createOneofWithoutInheritanceProto() {
        return OneofWithoutInheritanceProto.newBuilder()
                .setIntVal(3)
                .build();
    }

    static public ConcreteMapToMessageDomain createConcreteMapToMessageDomain() {
        ConcreteMapToMessageDomain domain = new ConcreteMapToMessageDomain();
        domain.setMapToMessage(Map.of(
                "aa", createPrimitiveDomain(),
                "bb", createPrimitiveDomain()));
        return domain;
    }

    static public ConcreteMapToMessageProto createConcreteMapToMessageProto() {
        return ConcreteMapToMessageProto.newBuilder()
                .putMapToMessage("aa", createPrimitivesProto())
                .putMapToMessage("bb", createPrimitivesProto())
                .build();
    }

    static public ConcretePrimitiveMapDomain createConcretePrimitiveMapDomain() {
        ConcretePrimitiveMapDomain domain = new ConcretePrimitiveMapDomain();
        domain.setPrimitiveMap(Map.of(1, 2L, 3, 4L));
        return domain;
    }

    static public ConcretePrimitiveMapProto createConcretePrimitiveMapProto() {
        return ConcretePrimitiveMapProto.newBuilder()
                .putPrimitiveMap(1, 2)
                .putPrimitiveMap(3, 4)
                .build();
    }

    static public MapToMessageDomain createMapToMessageDomain() {
        MapToMessageDomain domain = new MapToMessageDomain();
        domain.setMapToMessage(Map.of(
                "aa", createPrimitiveDomain(),
                "bb", createPrimitiveDomain()));
        return domain;
    }

    static public MapToMessageProto createMapToMessageProto() {
        return MapToMessageProto.newBuilder()
                .putMapToMessage("aa", createPrimitivesProto())
                .putMapToMessage("bb", createPrimitivesProto())
                .build();
    }

    static public PrimitiveMapDomain createPrimitiveMapDomain() {
        PrimitiveMapDomain domain = new PrimitiveMapDomain();
        domain.setPrimitiveMap(Map.of(1, 2L, 3, 4L));
        return domain;
    }

    static public PrimitiveMapProto createPrimitiveMapProto() {
        return PrimitiveMapProto.newBuilder()
                .putPrimitiveMap(1, 2)
                .putPrimitiveMap(3, 4)
                .build();
    }

    static public PrimitiveDomain createPrimitiveDomain() {
        PrimitiveDomain primitiveDomain = new PrimitiveDomain();
        primitiveDomain.setBooleanValue(true);
        primitiveDomain.setFloatValue(-0.1f);
        primitiveDomain.setDoubleValue(-0.5);
        primitiveDomain.setIntValue(-1);
        primitiveDomain.setLongValue(-2L);

        return primitiveDomain;
    }

    static public PrimitivesProto createPrimitivesProto() {
        return PrimitivesProto.newBuilder()
                .setBooleanValue(true)
                .setFloatValue(-0.1f)
                .setDoubleValue(-0.5)
                .setIntValue(-1)
                .setLongValue(-2L)
                .build();
    }

    static public StringDomain createStringDomain() {
        StringDomain stringDomain = new StringDomain();
        stringDomain.setStringValue("aaaa");
        return stringDomain;
    }

    static public StringProto createStringProto() {
        return StringProto.newBuilder().setStringValue("aaaa").build();
    }

    static public SimpleContainerDomain createSimpleContainerDomain() {
        SimpleContainerDomain simpleContainerDomain =  new SimpleContainerDomain();
        simpleContainerDomain.setPrimitives(createPrimitiveDomain());

        return simpleContainerDomain;
    }

    static public SimpleContainerProto createSimpleContainerProto() {
        return SimpleContainerProto.newBuilder()
                .setPrimitives(createPrimitivesProto())
                .build();
    }

    static public PrimitiveListDomain createPrimitiveListDomain() {
        PrimitiveListDomain listDomain = new PrimitiveListDomain();
        listDomain.setIntList(new ArrayList<>(List.of(1, 2, 3)));
        return listDomain;
    }

    static public PrimitiveListProto createPrimitiveListProto() {
        return PrimitiveListProto.newBuilder().addAllIntList(List.of(1, 2, 3)).build();
    }

    static public ConcretePrimitiveListDomain createConcretePrimitiveListDomain() {
        ConcretePrimitiveListDomain listDomain = new ConcretePrimitiveListDomain();
        listDomain.setIntList(new LinkedList<>(List.of(1, 2, 3)));
        return listDomain;
    }

    static public ConcretePrimitiveListProto createConcretePrimitiveListProto() {
        return ConcretePrimitiveListProto.newBuilder().addAllIntList(List.of(1, 2, 3)).build();
    }

    static public StringListDomain createStringListDomain() {
        StringListDomain listDomain = new StringListDomain();
        listDomain.setStringList(new ArrayList<>(List.of("aa", "bb", "cc")));
        return listDomain;
    }

    static public StringListProto createStringListProto() {
        return StringListProto.newBuilder().addAllStringList(List.of("aa", "bb", "cc")).build();
    }

    static public MessageListDomain createMessageListDomain() {
        MessageListDomain listDomain = new MessageListDomain();
        listDomain.setMessageList(new ArrayList<>(List.of(createPrimitiveDomain(), createPrimitiveDomain())));
        return listDomain;
    }

    static public MessageListProto createMessageListProto() {
        return MessageListProto.newBuilder()
                .addMessageList(createPrimitivesProto())
                .addMessageList(createPrimitivesProto())
                .build();
    }

    static public ConcreteMessageListDomain createConcreteMessageListDomain() {
        ConcreteMessageListDomain listDomain = new ConcreteMessageListDomain();
        listDomain.setMessageList(new LinkedList<>(List.of(createPrimitiveDomain(), createPrimitiveDomain())));
        return listDomain;
    }

    static public ConcreteMessageListProto createConcreteMessageListProto() {
        return ConcreteMessageListProto.newBuilder()
                .addMessageList(createPrimitivesProto())
                .addMessageList(createPrimitivesProto())
                .build();
    }
}
