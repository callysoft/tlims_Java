package com.tlimskech.marketplace.category.fashion;

import com.tlimskech.marketplace.core.data.BaseEnum;

public enum Gender implements BaseEnum {
    MALE("Male"), FEMALE("Female"), UNISEX("Unisex");

    private String description;

    Gender(String description) {
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
