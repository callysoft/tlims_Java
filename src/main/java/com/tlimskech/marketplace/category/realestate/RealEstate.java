package com.tlimskech.marketplace.category.realestate;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.CodeValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_real_estate")
@DiscriminatorValue("realestate")
@Active
public class RealEstate extends Ad {

    private Boolean brokerFeeFg;
    private String squareMeter;
    private Boolean parkingSpaceFg;
    @Enumerated(EnumType.STRING)
    private FurnishType furnishType;
    private String capacity;
    @ElementCollection
    @CollectionTable(name = "cat_real_estate_fcty")
    private List<CodeValue> facilities = new ArrayList<>();
    private Boolean contactForPrice;
    private Integer totalRoom;
    private Integer totalBathroom;
    private Boolean petsAllowed;
    private Boolean smokingAllowed;
}
