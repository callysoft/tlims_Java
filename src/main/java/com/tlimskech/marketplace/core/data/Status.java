package com.tlimskech.marketplace.core.data;

public enum Status implements BaseEnum {
    SUCCESS("Successful"), FAILED("Failed"), CONFIRM("Confirmed"), ONGOING("Ongoing"),
    LOST("Lost"), WON("Won"), PENDING("Pending");

    private String description;

    Status(String description) {
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
