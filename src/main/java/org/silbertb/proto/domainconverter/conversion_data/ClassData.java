package org.silbertb.proto.domainconverter.conversion_data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Accessors(fluent = true)
@Getter
@Builder
public class ClassData {
    final private String domainFullName;
    final private String domainClass;
    final private String protoFullName;
    final private String protoClass;
    final private String mapperClass;
    final private String mapperFullName;
    final private OneofBaseClassData oneofBaseClassData;

    final private List<FieldData> fieldsData;
    final private List<OneofBaseFieldData> oneofBaseFieldsData;
    final private List<ParameterData> constructorParameters;


    public boolean hasMapper() {
        return mapperClass != null;
    }

}
