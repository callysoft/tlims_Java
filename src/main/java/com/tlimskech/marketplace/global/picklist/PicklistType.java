package com.tlimskech.marketplace.global.picklist;

import com.tlimskech.marketplace.core.data.BaseEnum;

public enum PicklistType implements BaseEnum {

    COUNTRY("Country"), STATE("State"), BRAND("Brand"), ITEM_TYPE("Item Type"), ITEM_MAKE("Item Make"), MODEL("Model"),
    MATERIAL("Material"), COLOR("Color"), CLOSURE("Closure"), SIZE("Size"), STYLE("Style"), STONE("Stone"), FASTEN("Fastening"),
    FEATURE("Features"), OS("Operating System"), STORE_TYPE("Storage Type"), STORE_CAPACITY("Storage Capacity"), RAM("Ram"), CORES("Cores"),
    PLATFORM("Platform"), RATING("Rating"), YEAR("Year"), VOLUME("Volume"), FORMULA("Formulation"), SCENT("Scent"),
    TONE("Tone"), SKIN("Skin Type"), BENEFIT("Benefits"), TARGET_AREA("Target Area"), AGE_GROUP("Age Group"), PACKAGE("Package"), PROCESSOR("Processor");

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
