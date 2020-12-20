package com.forescout.proto.domainconverter;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {
    public static String lowerUnderscoreToPascalCase(String str) {
        return Arrays.stream(str.split("_"))
                .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase())
                .collect(Collectors.joining());
    }

    public static String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    public static String getPackage(String classFullName) {
        int index = classFullName.lastIndexOf('.');
        return classFullName.substring(0, index);
    }

    public static String getSimpleName(String classFullName) {
        int index = classFullName.lastIndexOf('.');
        return classFullName.substring(index+1);
    }
}
