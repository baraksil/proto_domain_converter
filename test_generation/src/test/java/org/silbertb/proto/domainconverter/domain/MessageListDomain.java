package org.silbertb.proto.domainconverter.domain;

import org.silbertb.proto.domainconverter.annotations.ProtoClass;
import org.silbertb.proto.domainconverter.annotations.ProtoField;
import org.silbertb.proto.domainconverter.test.proto.MessageListProto;
import lombok.Data;

import java.util.List;

@Data
@ProtoClass(protoClass = MessageListProto.class)
public class MessageListDomain {

    @ProtoField
    private List<PrimitiveDomain> messageList;
}
