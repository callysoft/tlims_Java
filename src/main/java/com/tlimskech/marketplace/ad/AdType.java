package com.tlimskech.marketplace.ad;

import com.tlimskech.marketplace.core.data.BaseEnum;

public enum AdType implements BaseEnum {

    PERSONAL("Personal"), BUSINESS("Business");

    private String description;

    AdType(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name();
    }
}
