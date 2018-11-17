package com.tlimskech.marketplace.global.picklist;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.BaseModel;
import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.valueobject.Code;
import com.tlimskech.marketplace.core.valueobject.TitleDescription;
import com.tlimskech.marketplace.global.category.Category;
import com.tlimskech.marketplace.global.category.QCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "gb_picklist")
@Active
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
    @Enumerated(EnumType.STRING)
    @Column(name = "prnt_pcklst_type")
    private PicklistType parentpickListType;
    @OneToOne
    @JoinColumn(name = "parent_id")
    private Picklist parentList;
    @OneToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;
    @OneToOne
    @JoinColumn(name = "sub_cat_id", nullable = true)
    private Category subCategory;

    public BooleanExpression searchPredicate(SearchRequest request) {
        QPicklist qPicklist = QPicklist.picklist;
        if (StringUtils.isEmpty(request.getSearchTerm())) return qPicklist.isNotNull();
        return qPicklist.pickListcode.dataCode.containsIgnoreCase(request.getSearchTerm())
                .or(qPicklist.titleDescription.title.containsIgnoreCase(request.getSearchTerm()))
                .or(qPicklist.titleDescription.description.containsIgnoreCase(request.getSearchTerm()))
                .or(qPicklist.picklistType.stringValue().containsIgnoreCase(request.getSearchTerm()));
    }

    public BooleanExpression predicate() {
        QPicklist qPicklist = QPicklist.picklist;
        return  qPicklist.category.eq(this.getCategory())
                .and(qPicklist.subCategory.eq(this.getSubCategory()))
                .and(qPicklist.picklistType.eq(this.getPicklistType()));
    }
}
