package com.forescout.proto.domainconverter.domain.custom_mapper;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.domain.StringDomain;
import com.forescout.proto.domainconverter.test.proto.CustomMapperProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = CustomMapperProto.class, mapper = CustomMapper.class)
public class CustomMapperDomain {
    private boolean isTcp;
    private boolean isUdp;
    private StringDomain stringDomain;

    public void setUdp(boolean udp) {
        isUdp = udp;
        isTcp = !udp;
    }

    public void setTcp(boolean tcp) {
        isTcp = tcp;
        isUdp = !tcp;
    }
}
