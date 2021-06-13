package com.forescout.proto.domainconverter;

import com.forescout.proto.domainconverter.domain.*;
import com.forescout.proto.domainconverter.domain.custom_converter.CustomListConverterDomain;
import com.forescout.proto.domainconverter.domain.custom_converter.CustomMapConverterDomain;
import com.forescout.proto.domainconverter.domain.custom_mapper.CustomMapperDomain;
import com.forescout.proto.domainconverter.domain.oneof.class_with_members.OneofBaseClassWithFieldsDomain;
import com.forescout.proto.domainconverter.domain.oneof.class_with_members.OneofDoubleImplDomain;
import com.forescout.proto.domainconverter.domain.oneof.field.OneofIntImplDomain;
import com.forescout.proto.domainconverter.domain.oneof.field.OneofWithFieldInheritanceDomain;
import com.forescout.proto.domainconverter.domain.custom_converter.CustomConverterDomain;
import com.forescout.proto.domainconverter.domain.oneof.clazz.OneofBaseClassDomain;
import com.forescout.proto.domainconverter.domain.oneof.clazz.TwoIntsDomain;
import com.forescout.proto.domainconverter.test.proto.*;
import com.google.protobuf.ByteString;

import java.util.*;

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

    static public OneofWithFieldInheritanceDomain createOneofWithInheritanceDomain() {
        OneofWithFieldInheritanceDomain domain = new OneofWithFieldInheritanceDomain();
        OneofIntImplDomain oneofIntImplDomain = new OneofIntImplDomain();
        oneofIntImplDomain.setIntVal(3);
        domain.setValue(oneofIntImplDomain);
        return domain;
    }

    static public OneofWithFieldInheritanceProto createOneofWithFieldInheritanceProto() {
        return OneofWithFieldInheritanceProto.newBuilder()
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
        domain.setMapToMessage(new HashMap<>(Map.of(
                "aa", createPrimitiveDomain(),
                "bb", createPrimitiveDomain())));

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
        domain.setPrimitiveMap(new HashMap<>(Map.of(1, 2L, 3, 4L)));
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

    static public CustomConverterDomain createCustomConverterDomain() {
        CustomConverterDomain domain = new CustomConverterDomain();
        domain.setStrVal("5");
        return domain;
    }

    static public CustomConverterProto createCustomConverterProto() {
        return CustomConverterProto.newBuilder().setIntVal(5).build();
    }

    static public CustomListConverterDomain createCustomListConverterDomain() {
        CustomListConverterDomain domain = new CustomListConverterDomain();
        domain.setCommaSeparatedInt("5,6");
        return domain;
    }

    static public CustomListConverterProto createCustomListConverterProto() {
        return CustomListConverterProto.newBuilder().addIntList(5).addIntList(6).build();
    }

    static public CustomMapConverterDomain createCustomMapConverterDomain() {
        CustomMapConverterDomain domain = new CustomMapConverterDomain();
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "2");
        map.put("3", "4");

        domain.setMap(map);
        return domain;
    }

    static public CustomMapConverterProto createCustomMapConverterProto() {
        return CustomMapConverterProto.newBuilder().putIntMap(1,2).putIntMap(3,4).build();
    }

    static public CustomMapperDomain createCustomMapperDomain() {
        CustomMapperDomain domain = new CustomMapperDomain();
        domain.setUdp(true);
        StringDomain stringDomain = new StringDomain();
        stringDomain.setStringValue("aaa");
        domain.setStringDomain(stringDomain);

        return domain;
    }

    static public CustomMapperProto createCustomMapperProto() {
        return CustomMapperProto.newBuilder()
                .setProtocol(CustomMapperProto.Protocol.UDP)
                .setStr(StringProto.newBuilder().setStringValue("aaa"))
                .build();
    }

    public static OneofBaseClassProto createOneofBaseClassProto() {
        return OneofBaseClassProto.newBuilder()
                .setTwoIntsVal(TwoIntsProto.newBuilder().setIntVal1(1).setIntVal2(2))
                .build();
    }


    public static OneofBaseClassDomain createOneofbaseClassDomain() {
        TwoIntsDomain domain = new TwoIntsDomain();
        domain.setIntVal1(1);
        domain.setIntVal2(2);
        return domain;
    }

    public static OneofBaseClassWithFieldsProto OneofBaseClassWithFieldsProto() {
        return OneofBaseClassWithFieldsProto.newBuilder()
                .setStringVal("aaa")
                .setDoubleVal(0.5)
                .build();
    }

    public static OneofBaseClassWithFieldsDomain createOneofbaseClassWithFieldsDomain() {
        OneofDoubleImplDomain domain = new OneofDoubleImplDomain();
        domain.setStringVal("aaa");
        domain.setDoubleVal(0.5);
        return domain;
    }
}
