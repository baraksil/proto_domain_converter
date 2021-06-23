package org.silbertb.proto.domainconverter.conversion_data;

import java.util.ArrayList;
import java.util.List;

public class OneofBaseFieldData {
    public String domainFieldType;
    public String oneofBaseField;
    public String oneofProtoName;
    public List<OneofFieldData> oneOfFieldsData = new ArrayList<>();
}
