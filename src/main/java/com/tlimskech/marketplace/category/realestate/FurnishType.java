package com.tlimskech.marketplace.category.realestate;

import com.tlimskech.marketplace.core.data.BaseEnum;

public enum FurnishType implements BaseEnum {
    FURNISHED("Furnished"), SEMI("Semi-Furnished"), UNFURNISHED("Unfurnished");

    private String description;

    FurnishType(String description) {
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
