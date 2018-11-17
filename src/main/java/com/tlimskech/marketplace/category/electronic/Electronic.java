package com.tlimskech.marketplace.category.electronic;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.CodeValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_electronic")
@Active
@DiscriminatorValue("electronic")
public class Electronic extends Ad {

    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "elect_make_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "elect_make"))})
    private CodeValue electMake;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "model_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "model"))})
    private CodeValue electModel;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "sub_type_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "sub_type"))})
    private CodeValue electSubType;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "pcssr_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "pcssr"))})
    private CodeValue processor;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "cores_no_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "cores_no"))})
    private CodeValue coreNo;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "ram_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "ram"))})
    private CodeValue ram;
}
