package com.forescout.proto.domainconverter;

import java.util.ArrayList;
import java.util.List;

public class ConversionData {
    String generator;
    String converterPackage;
    String converterClass;
    List<ClassData> classesData = new ArrayList<>();

    public ConversionData(String generator, String converterPackage, String converterClass) {
        this.converterPackage = converterPackage;
        this.converterClass = converterClass;
        this.generator = generator;
    }

    static class ClassData {
        String domainFullName;
        String domainClass;
        String protoFullName;
        String protoClass;
        List<FieldData> fieldsData = new ArrayList<>();
    }

    static class FieldData {
        String protoSetterMethod;
        String domainGetterMethod;
    }
}
