package com.tlimskech.marketplace.core.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NTuple extends com.tlimskech.marketplace.core.data.Data {

    private String category;
    private String subCategory;
    private String subCatType;
    private String itemCondition;
    private String price;
    private String brands;
    private Paging paging;
}
