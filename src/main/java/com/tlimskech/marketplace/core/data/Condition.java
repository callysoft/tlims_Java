package com.tlimskech.marketplace.core.data;

public enum Condition implements BaseEnum {

    NEW("New"), FAIRLY("Fairly Used"), USED("Used"), NIGERIAN("Nigerian Used"), TOKUNBO("Tokunbo or Foreign used");

    
 


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
