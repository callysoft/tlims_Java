package com.tlimskech.marketplace.global.category;

import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.BaseModel;
import com.tlimskech.marketplace.core.valueobject.Code;
import com.tlimskech.marketplace.core.valueobject.TitleDescription;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "gb_category")
@Active
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends BaseModel {

    @Embedded
    @Valid
    private Code categoryCode;
    @Embedded
    @Valid
    private TitleDescription titleDescription;
    @OneToOne
    @JoinColumn(name = "parent_id")
    private Category parentCategory;

}
