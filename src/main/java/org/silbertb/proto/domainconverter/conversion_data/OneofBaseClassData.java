package org.silbertb.proto.domainconverter.conversion_data;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class OneofBaseClassData {
    final private String oneofProtoName;
    final private List<OneofFieldData> oneOfFieldsData;
}
