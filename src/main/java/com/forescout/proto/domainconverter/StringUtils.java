package com.forescout.proto.domainconverter;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {
    public static String snakeCaseToPascalCase(String str) {
        return Arrays.stream(str.split("_"))
                .map(s -> Character.toUpperCase(s.charAt(0)) + s.substring(1).toLowerCase())
                .collect(Collectors.joining());
    }

    public static String pascalCaseToSnakeCase(String str) {
        // Regular Expression
        String regex = "([a-z])([A-Z]+)";

        // Replacement string
        String replacement = "$1_$2";

        // Replace the given regex
        // with replacement string
        // and convert it to lower case.
        str = str
                .replaceAll(
                        regex, replacement)
                .toLowerCase();

        // return string
        return str;
    }
    public static String capitalize(String str) {
        if(str.isEmpty()) {
            return str;
        }
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

    public static String pascalCaseToCamelCase(String str) {
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }
}
