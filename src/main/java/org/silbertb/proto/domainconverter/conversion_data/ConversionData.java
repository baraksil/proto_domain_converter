package org.silbertb.proto.domainconverter.conversion_data;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(fluent = true)
@Getter
@Builder
public class ConversionData {
    private final String generator;
    private final String converterPackage;
    private final String converterClass;
    private final List<ClassData> classesData;
}
