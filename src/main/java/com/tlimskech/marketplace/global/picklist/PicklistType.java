package com.tlimskech.marketplace.global.picklist;

import com.tlimskech.marketplace.core.data.BaseEnum;

public enum PicklistType implements BaseEnum {

    COUNTRY("Country"), STATE("State");

    private String description;

    PicklistType(String description) {
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
