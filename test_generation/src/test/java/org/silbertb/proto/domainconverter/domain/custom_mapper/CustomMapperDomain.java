package org.silbertb.proto.domainconverter.domain.custom_mapper;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.domain.StringDomain;
import org.silbertb.proto.domainconverter.test.proto.CustomMapperProto;
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
