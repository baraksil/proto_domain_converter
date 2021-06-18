package org.silbertb.proto.domainconverter.domain.oneof.class_with_members;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class OneofLongImplDomain extends OneofBaseClassWithFieldsDomain {
    private long longVal;
}
