package com.tlimskech.marketplace.ad;

import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.BaseEntity;
import com.tlimskech.marketplace.core.data.Condition;
import com.tlimskech.marketplace.core.data.Money;
import com.tlimskech.marketplace.core.valueobject.TitleDescription;
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

    @Valid
    @Embedded
    private TitleDescription titleDescription;
    @ElementCollection
    @CollectionTable(name = "ad_image")
    private List<String> images = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private AdType adType;
    @NotNull(message = "category is required")
    @Column(nullable = false)
    private String category;
    @NotNull(message = "sub-category is required")
    @Column(nullable = false)
    private String subCategory;
    @Enumerated(EnumType.STRING)
    private Condition itemCondition;
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "price_ccy", length = 3)),
            @AttributeOverride(name = "amount", column = @Column(name = "price_amt", scale = 10, precision = 38, unique = false))})
    private Money price;
    private boolean negotiable;
}
