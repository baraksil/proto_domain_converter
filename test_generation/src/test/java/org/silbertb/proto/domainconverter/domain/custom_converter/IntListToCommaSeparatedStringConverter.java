package org.silbertb.proto.domainconverter.domain.custom_converter;

import org.silbertb.proto.domainconverter.custom.TypeConverter;

import java.util.ArrayList;
import java.util.List;

public class IntListToCommaSeparatedStringConverter implements TypeConverter<String, List<Integer>> {
    @Override
    public String toDomainValue(List<Integer> protoValue) {
        if(protoValue.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for(Integer v : protoValue) {
            sb.append(v);
            sb.append(',');
        }
        sb.deleteCharAt(sb.length()-1);

        return sb.toString();
    }

    @Override
    public boolean shouldAssignToProto(String domainValue) {
        return domainValue != null;
    }

    @Override
    public List<Integer> toProtobufValue(String domainValue) {
        List<Integer> intList = new ArrayList<>();
        for(String str : domainValue.split(",")) {
            intList.add(Integer.parseInt(str));
        }
        return intList;
    }
}
