package com.tlimskech.marketplace.global.category;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.BaseModel;
import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.valueobject.Code;
import com.tlimskech.marketplace.core.valueobject.TitleDescription;
import lombok.*;
import org.springframework.util.StringUtils;

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

    public BooleanExpression searchPredicate(SearchRequest request) {
        QCategory qCategory = QCategory.category;
        if (StringUtils.isEmpty(request.getSearchTerm())) return qCategory.isNotNull();
        return qCategory.categoryCode.dataCode.containsIgnoreCase(request.getSearchTerm())
                .or(qCategory.titleDescription.title.containsIgnoreCase(request.getSearchTerm()))
                .or(qCategory.titleDescription.description.containsIgnoreCase(request.getSearchTerm()))
                .or(qCategory.status.stringValue().containsIgnoreCase(request.getSearchTerm()));
    }
}
