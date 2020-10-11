package com.tlimskech.marketplace.auth.token;

import com.tlimskech.marketplace.core.data.BaseEnum;

public enum TokenType implements BaseEnum {

    PASSWORD_RESET_LINK("Password Reset Link"), PASSWORD_RESET_TOKEN("Password Reset Token"),
    SOFT_TOKEN("Soft Token"), FIRST_LOGIN("First Time Login");
    private final String description;

    TokenType(String s) {
        this.description = s;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getName() {
        return name();
    }
}
