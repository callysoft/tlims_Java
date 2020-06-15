package com.tlimskech.marketplace.newsletter;

import com.tlimskech.marketplace.core.data.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "app_newsletter")
@Data
public class NewsLetter extends BaseModel {

    private String message;
}
