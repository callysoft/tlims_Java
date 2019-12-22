package com.tlimskech.marketplace.category.mobile;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.CodeValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_mobile")
@DiscriminatorValue("mobile")
@Active
public class Mobile extends Ad {

    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "model_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "model_nm"))})
    private CodeValue model;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "cap_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "cap_nm"))})
    private CodeValue storageCapacity;
    private String color;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "ssize_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "ssize_nm"))})
    private CodeValue screenSize;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "ram_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "ram_nm"))})
    private CodeValue ram;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "os_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "os_nm"))})
    private CodeValue os;
    private Boolean isExchangeable;

}
