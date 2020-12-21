package com.forescout.proto.domainconverter.conversion_data;

public class OneofFieldData {
    public boolean fieldIsMessage;
    public String domainBaseField;
    public String oneofImplClass;
    public String oneOfProtoField;
    public String oneOfDomainField;
    public String addToBuilderCommand() {
        if(fieldIsMessage) {
            return "builder.set" + oneOfProtoField + "(toProto((" + oneofImplClass + ")domain.get" + domainBaseField + "()))";
        }

        return "builder.set" + oneOfProtoField + "(((" + oneofImplClass + ")domain.get" + domainBaseField + "()).get" + oneOfDomainField + "())";
    }
}
