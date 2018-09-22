package com.tlimskech.marketplace.core.data;

public enum Condition implements BaseEnum {

    NEW("New"), REPLICA("Replica");

    private String description;

    Condition(String description) {
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
