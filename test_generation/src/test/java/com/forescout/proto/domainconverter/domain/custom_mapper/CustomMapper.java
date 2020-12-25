package com.forescout.proto.domainconverter.domain.custom_mapper;

import com.forescout.proto.domainconverter.custom.Mapper;
import com.forescout.proto.domainconverter.domain.StringDomain;
import com.forescout.proto.domainconverter.generated.ProtoDomainConverter;
import com.forescout.proto.domainconverter.test.proto.CustomMapperProto;

public class CustomMapper implements Mapper<CustomMapperDomain, CustomMapperProto> {
    @Override
    public CustomMapperDomain toDomain(CustomMapperProto protoValue) {
        CustomMapperDomain domain = new CustomMapperDomain();
        domain.setTcp(protoValue.getProtocol().equals(CustomMapperProto.Protocol.TCP));
        domain.setUdp(protoValue.getProtocol().equals(CustomMapperProto.Protocol.UDP));


        StringDomain stringDomain = ProtoDomainConverter.toDomain(protoValue.getStr());
        domain.setStringDomain(stringDomain);

        return domain;
    }

    @Override
    public CustomMapperProto toProto(CustomMapperDomain domainValue) {

        return CustomMapperProto.newBuilder()
                .setProtocol(domainValue.isTcp() ? CustomMapperProto.Protocol.TCP : CustomMapperProto.Protocol.UDP)
                .setStr(ProtoDomainConverter.toProto(domainValue.getStringDomain()))
                .build();
    }
}
