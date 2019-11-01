package com.tlimskech.marketplace.core.valueobject.facebook_vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tlimskech.marketplace.core.data.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@JsonSerialize
@lombok.Data
public class FbPicture extends Data {
    private FbPicData data;
}
