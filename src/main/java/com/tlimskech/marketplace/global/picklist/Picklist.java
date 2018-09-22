package com.tlimskech.marketplace.global.picklist;

import com.tlimskech.marketplace.core.data.BaseModel;
import com.tlimskech.marketplace.core.valueobject.Code;
import com.tlimskech.marketplace.core.valueobject.TitleDescription;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "gb_picklist")
public class Picklist extends BaseModel {

    @Embedded
    @Valid
    private Code pickListcode;
    @Embedded
    @Valid
    private TitleDescription titleDescription;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private PicklistType picklistType;
    @OneToOne
    @JoinColumn(name = "parent_id")
    private Picklist parentList;
}
