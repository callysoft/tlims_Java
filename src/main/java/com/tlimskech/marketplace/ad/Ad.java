package com.tlimskech.marketplace.ad;

import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.BaseEntity;
import com.tlimskech.marketplace.core.data.Condition;
import com.tlimskech.marketplace.core.data.Money;
import com.tlimskech.marketplace.core.valueobject.Code;
import com.tlimskech.marketplace.core.valueobject.TitleDescription;
import com.tlimskech.marketplace.global.category.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Active
@Entity
@Table(name = "ad_item")
@Data
public class Ad extends BaseEntity {

    @Embedded
    @Valid
    private Code adCode;
    @Valid
    @Embedded
    private TitleDescription titleDescription;
    @ElementCollection
    @CollectionTable(name = "ad_image")
    private List<String> images = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private AdType adType;
    @NotNull(message = "category is required")
    @JoinColumn(nullable = false, name = "cat_id")
    @OneToOne
    private Category category;
    @NotNull(message = "sub-category is required")
    @JoinColumn(nullable = false, name = "sub_cat_id")
    @OneToOne
    private Category subCategory;
    @Enumerated(EnumType.STRING)
    private Condition itemCondition;
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "price_ccy", length = 3)),
            @AttributeOverride(name = "amount", column = @Column(name = "price_amt", scale = 10, precision = 38, unique = false))})
    private Money price;
    private boolean negotiable;
}
