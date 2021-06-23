package org.silbertb.proto.domainconverter.conversion_data;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Accessors(fluent = true)
@Getter
@Builder
public class OneofBaseFieldData {
    final private String domainFieldType;
    final private String oneofBaseField;
    final private String oneofProtoName;
    final private List<OneofFieldData> oneOfFieldsData;
}
