package com.tlimskech.marketplace.core.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataGroup extends com.tlimskech.marketplace.core.data.Data {

    private String code;
    private String name;
    private Long count;
}
