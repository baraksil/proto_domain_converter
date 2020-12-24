package com.forescout.proto.domainconverter.domain.custom_converter;

import com.forescout.proto.domainconverter.custom.TypeConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class IntMapStringMapConverter implements TypeConverter<HashMap<String, String>, Map<Integer, Integer>> {
    @Override
    public HashMap<String, String> toDomainValue(Map<Integer, Integer> protoValue) {
        HashMap<String, String> map = new HashMap<>();
        protoValue.forEach((key, val) -> map.put(key.toString(), val.toString()));
        return map;
    }

    @Override
    public boolean shouldAssignToProto(HashMap<String, String> domainValue) {
        return domainValue != null;
    }

    @Override
    public Map<Integer, Integer> toProtobufValue(HashMap<String, String> domainValue) {
        return domainValue.entrySet().stream().collect(Collectors.toMap(e -> Integer.parseInt(e.getKey()), e -> Integer.parseInt(e.getValue())));
    }
}
