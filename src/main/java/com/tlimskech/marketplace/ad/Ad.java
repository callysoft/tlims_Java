package com.tlimskech.marketplace.ad;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.tlimskech.marketplace.core.data.*;
import com.tlimskech.marketplace.core.valueobject.TitleDescription;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;

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
    @Transient
    private String amount;
    @Transient
    private String brands;
    @Embedded
    private Contact contact;

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

    public BooleanBuilder filterPredicates() {
        QAd qAd = QAd.ad;
        BooleanBuilder builder = new BooleanBuilder();
        if (!ObjectUtils.isEmpty(this.category) && !isEmpty(this.category.getCode())) {
            builder.and(qAd.category.code.eq(this.category.getCode()));
        }
        if (!ObjectUtils.isEmpty(this.subCategory) && !isEmpty(this.subCategory.getCode())) {
            builder.and(qAd.subCategory.code.eq(this.subCategory.getCode()));
        }
        if (!ObjectUtils.isEmpty(this.brand) && !isEmpty(this.brand.getCode())) {
            builder.and(qAd.brand.code.eq(this.brand.getCode()));
        }
        if (!ObjectUtils.isEmpty(this.getItemCondition())) {
            builder.and(qAd.itemCondition.eq(this.getItemCondition()));
        }
        if (!isEmpty(this.amount)) {
            if (this.amount.contains("-")) {
                String[] amounts = this.amount.split("-");
                if (!isEmpty(amounts[0]) && !isEmpty(amounts[1])) {
                    builder.and(qAd.price.amount.between(new BigDecimal(amounts[0]), new BigDecimal(amounts[1])));
                }
                if (!isEmpty(amounts[0]) && isEmpty(amounts[1])) {
                    builder.and(qAd.price.amount.between(new BigDecimal(amounts[0]), new BigDecimal("9999999999999999999999999999")));
                }
                if (isEmpty(amounts[0]) && !isEmpty(amounts[1])) {
                    builder.and(qAd.price.amount.between(BigDecimal.ZERO, new BigDecimal(amounts[1])));
                }
            } else {
                builder.and(qAd.price.amount.between(new BigDecimal(this.amount), new BigDecimal("9999999999999999999999999999")));
            }

        }
        return builder;
    }
}
