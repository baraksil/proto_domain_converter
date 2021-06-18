package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.BytesProto;
import lombok.Data;

@Data
@ProtoClass(protoClass = BytesProto.class)
public class BytesDomain {
    @ProtoField
    byte[] bytesValue;
}
