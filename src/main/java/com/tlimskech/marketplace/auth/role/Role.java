package com.tlimskech.marketplace.auth.role;

import com.tlimskech.marketplace.core.data.BaseEnum;

public enum Role implements BaseEnum {

    SUPER_ADMIN("Super Admin"), ADMIN("Admin"), MEMBER("Member");

    private String description;

    Role(String description) {
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
