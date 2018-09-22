package com.tlimskech.marketplace.core.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@lombok.Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest extends Data {

    private String searchTerm;
}
