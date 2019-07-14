package com.tlimskech.marketplace.category.job;

import com.tlimskech.marketplace.ad.Ad;
import com.tlimskech.marketplace.core.data.Active;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "cat_job")
@DiscriminatorValue("job")
@Active
public class Job extends Ad {

    private String companyName;
    private String jobType;
    private String minimumExp;
    @Column(length = 5000)
    private String responsibilities;
    @Column(length = 5000)
    private String requirements;
    @Column(length = 5000)
    private String miniQualification;
    private String salaryFrom;
    private String salaryTo;
}
