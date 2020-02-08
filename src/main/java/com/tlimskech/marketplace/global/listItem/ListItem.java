package com.tlimskech.marketplace.global.listItem;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.tlimskech.marketplace.core.data.CodeValue;
import com.tlimskech.marketplace.core.data.Data;
import com.tlimskech.marketplace.core.data.SearchRequest;
import com.tlimskech.marketplace.core.valueobject.Code;
import com.tlimskech.marketplace.core.valueobject.TitleDescription;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@lombok.Data
@Entity
@Table(name = "gb_list")
public class ListItem extends Data {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Embedded
    @Valid
    private Code listCode;
    @Embedded
    @Valid
    private TitleDescription titleDescription;
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private ListItemType itemType;
    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "code", column = @Column(name = "cat_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "cat_nm")) })
    private CodeValue category;
    @ElementCollection
    @CollectionTable(name = "gb_list_subcat")
    private List<CodeValue> subCategories = new ArrayList<>();
    @OneToOne
    private ListItem parentList;
    private Boolean hasParent;
    private Boolean hasManySubCategory;
    @Enumerated(EnumType.STRING)
    private ListItemType parentItemType;
    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "code", column = @Column(name = "prt_subcat_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "prt_subcat_nm")) })
    private CodeValue parentSubCat;

    public BooleanExpression searchPredicate(SearchRequest request) {
        QListItem qPicklist = QListItem.listItem;
        if (StringUtils.isEmpty(request.getSearchTerm())) return qPicklist.isNotNull();
        return qPicklist.listCode.dataCode.containsIgnoreCase(request.getSearchTerm())
                .or(qPicklist.titleDescription.title.containsIgnoreCase(request.getSearchTerm()))
                .or(qPicklist.titleDescription.description.containsIgnoreCase(request.getSearchTerm()))
                .or(qPicklist.itemType.stringValue().containsIgnoreCase(request.getSearchTerm()));
    }
}
