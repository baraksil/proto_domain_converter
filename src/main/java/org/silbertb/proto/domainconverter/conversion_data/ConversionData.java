package org.silbertb.proto.domainconverter.conversion_data;

import java.util.ArrayList;
import java.util.List;

public class ConversionData {
    public String generator;
    public String converterPackage;
    public String converterClass;
    public List<ClassData> classesData = new ArrayList<>();

    public ConversionData(String generator, String converterPackage, String converterClass) {
        this.converterPackage = converterPackage;
        this.converterClass = converterClass;
        this.generator = generator;
    }

    public static class ClassData {
        public String domainFullName;
        public String domainClass;
        public String protoFullName;
        public String protoClass;
        public String mapperClass;
        public String mapperFullName;
        public OneofBaseClassData oneofBaseClassData;

        public List<FieldData> fieldsData = new ArrayList<>();
        public List<OneofBaseFieldData> oneofBaseFieldsData = new ArrayList<>();
        public boolean hasMapper() {
            return mapperClass != null;
        }
    }

    public enum FieldType {
        MESSAGE,
        BOOLEAN,
        STRING,
        BYTES,
        PRIMITIVE_LIST,
        MESSAGE_LIST,
        PRIMITIVE_MAP,
        MAP_TO_MESSAGE,
        OTHER
    }

}
