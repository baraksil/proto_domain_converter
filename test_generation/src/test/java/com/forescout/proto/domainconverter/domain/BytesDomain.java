package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.BytesProto;
import com.google.protobuf.ByteString;
import lombok.Data;

@Data
@ProtoClass(protoClass = BytesProto.class)
public class BytesDomain {
    @ProtoField
    byte[] bytesValue;
}
