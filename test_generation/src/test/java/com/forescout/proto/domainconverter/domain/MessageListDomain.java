package com.forescout.proto.domainconverter.domain;

import com.forescout.proto.domainconverter.annotations.ProtoClass;
import com.forescout.proto.domainconverter.annotations.ProtoField;
import com.forescout.proto.domainconverter.test.proto.MessageListProto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ProtoClass(protoClass = MessageListProto.class)
public class MessageListDomain {

    @ProtoField
    private List<PrimitiveDomain> messageList;
}
