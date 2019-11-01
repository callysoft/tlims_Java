package com.tlimskech.marketplace.core.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest extends Data {

    private String searchTerm;
    private String category;
}
