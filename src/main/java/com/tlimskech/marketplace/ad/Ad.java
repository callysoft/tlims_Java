package com.tlimskech.marketplace.ad;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.tlimskech.marketplace.core.data.*;
import com.tlimskech.marketplace.core.valueobject.TitleDescription;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

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
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "ad_category", discriminatorType = DiscriminatorType.STRING)
public class Ad extends BaseEntity {

    @Valid
    @Embedded
    private TitleDescription titleDescription;
    @ElementCollection
    @CollectionTable(name = "ad_image")
    private List<String> images = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private AdType adType;
    @NotNull(message = "Category is Required")
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "cat_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "cat"))})
    private CodeValue category;
    @NotNull(message = "Subcategory is Required")
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "sub_cat_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "sub_cat"))})
    private CodeValue subCategory;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "cat_type_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "sub_cat_type"))})
    private CodeValue subCatType;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "brand_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "brand"))})
    private CodeValue brand;
    @Enumerated(EnumType.STRING)
    private Condition itemCondition;
    @AttributeOverrides({@AttributeOverride(name = "currency", column = @Column(name = "price_ccy", length = 3)),
            @AttributeOverride(name = "amount", column = @Column(name = "price_amt", scale = 10, precision = 38))})
    private Money price;
    private Boolean negotiable;
    private Boolean authorized;
    private Boolean featured;
    private Boolean archived;

    public BooleanExpression predicates(SearchRequest request) {
        QAd qAd = QAd.ad;
        if (StringUtils.isEmpty(request.getSearchTerm())) {
            return qAd.isNotNull();
        }
        return qAd.price.amount.stringValue().containsIgnoreCase(request.getSearchTerm())
                .or(qAd.titleDescription.title.containsIgnoreCase(request.getSearchTerm()))
                .or(qAd.titleDescription.description.containsIgnoreCase(request.getSearchTerm()))
                .or(qAd.brand.name.containsIgnoreCase(request.getSearchTerm()))
                .or(qAd.category.name.containsIgnoreCase(request.getSearchTerm()))
                .or(qAd.subCategory.name.containsIgnoreCase(request.getSearchTerm()))
                .or(qAd.subCatType.name.containsIgnoreCase(request.getSearchTerm()));
    }

    public BooleanExpression allAds() {
        return QAd.ad.authorized.isTrue();
    }
}
