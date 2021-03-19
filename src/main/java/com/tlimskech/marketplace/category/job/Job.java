package com.tlimskech.marketplace.category.job;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.CodeValue;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_job")
@DiscriminatorValue("job")
@Active
public class Job extends Ad {

    private String companyName;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "job_type_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "job_type"))})
    private CodeValue jobType;
    @AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "min_exp_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "min_exp"))})
    private CodeValue minimumExp;
@AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "others_cd")),
            @AttributeOverride(name = "name", column = @Column(name = "others"))})
    private CodeValue others;
    @Column(length = 5000)
    private String responsibilities;
    @Column(length = 5000)
    private String requirements;
    @Column(length = 5000)
    private String miniQualification;
    private String salaryFrom;
    private String salaryTo;
}
