package com.tlimskech.marketplace.category.vehicle;

import com.tlimskech.marketplace.core.data.BaseEnum;

public enum Transmission implements BaseEnum {

    AUTOMATIC("Automatic"), MANUAL("Manual");

    private String description;

    Transmission(String description) {
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
