package org.silbertb.proto.domainconverter.conversion_data;

import org.silbertb.proto.domainconverter.StringUtils;

public class OneofFieldData {
    public boolean fieldIsMessage;
    public String oneofFieldCase;
    public String domainBaseField;
    public String oneofImplClass;
    public String oneofImplClassSimple;
    public String oneOfProtoField;
    public String oneOfDomainField;

    public String addToBuilderCommand() {
        if(domainBaseField == null) { //class inheritance
            if(fieldIsMessage) {
                return "builder.set" + oneOfProtoField + "(toProto((" + oneofImplClassSimple + ")domain))";
            }

            return "builder.set" + oneOfProtoField + "(((" + oneofImplClassSimple + ")domain).get" + oneOfDomainField + "())";
        }

        //field inheritance
        if(fieldIsMessage) {
            return "builder.set" + oneOfProtoField + "(toProto((" + oneofImplClassSimple + ")domain.get" + domainBaseField + "()))";
        }

        return "builder.set" + oneOfProtoField + "(((" + oneofImplClassSimple + ")domain.get" + domainBaseField + "()).get" + oneOfDomainField + "())";
    }

    public String oneofImplVariable() {
        return StringUtils.pascalCaseToCamelCase(oneofImplClassSimple);
    }

    public String oneofImplCreation() {
        if(fieldIsMessage) {
            return "toDomain(proto.get" + oneOfProtoField + "())";
        }

        return "new " + oneofImplClassSimple + "()";
    }

    public String setValueToVariable() {
        if(fieldIsMessage) {
            return "";
        }

        return oneofImplVariable() + ".set" + oneOfDomainField + "(proto.get" + oneOfProtoField + "());";
    }
}
