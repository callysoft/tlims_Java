package com.tlimskech.marketplace.global.listItem;

import com.tlimskech.marketplace.core.data.BaseEnum;

public enum ListItemType implements BaseEnum {

    COUNTRY("Country"), STATE("State"), BRAND("Brand"), ITEM_TYPE("Item Type"), ITEM_MAKE("Item Make"), MODEL("Model"),
    MATERIAL("Material"), COLOR("Color"), CLOSURE("Closure"), SIZE("Size"), STYLE("Style"), MOVEMENT("Movement"),
    STONE("Stone"), FASTEN("Fastening"), OUTSOLE("Outsole Material"), POWER_SOURCE("PowerSource"), GRAPHIC_CARD("Graphic Card"),
    FEATURE("Features"), OS("Operating System"), STORE_TYPE("Storage Type"), SUB_TYPE("Sub-Type"),
    STORE_CAPACITY("Storage Capacity"), RAM("Ram"), CORES("Cores"), SHAPE("Shape"), DISPLAY("Display"),
    PLATFORM("Platform"), RATING("Rating"), YEAR("Year"), VOLUME("Volume"), FORMULA("Formulation"), SCENT("Scent"),
    TONE("Tone"), SKIN("Skin Type"), BENEFIT("Benefits"), TARGET_AREA("Target Area"),
    AGE_GROUP("Age Group"), PACKAGE("Package"), PROCESSOR("Processor"), BREED("Breed"), BREED_TYPE("Breed Type"),
    JOB_TYPE("Job Type"), JOB_EXPERIENCE("Years of Experience"), FACILITY("Facilities");

    private String description;

    ListItemType(String description) {
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
