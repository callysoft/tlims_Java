package com.tlimskech.marketplace.category.favorite;

import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "ad_favorites")
@Active
public class Favorite extends BaseEntity {

    private Long postId;
}
