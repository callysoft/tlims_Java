package com.tlimskech.marketplace.core.data;

import com.tlimskech.marketplace.core.util.Utils;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@lombok.Data
public class BaseEntity extends Data implements ICode{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;
    @LastModifiedBy
    @Column(nullable = false)
    private String modifiedBy;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @LastModifiedDate
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date LastModifiedDate;
    @Column(nullable = false)
    private Boolean status;
    @Column(name = "code", nullable = false, unique = true, updatable = false)
    private String code;
    @Version
    private Long version;
    @Transient
    private Date fromDate, toDate;

    @PrePersist
    public void setCode() {
        Active active = this.getClass().getDeclaredAnnotation(Active.class);
        this.setStatus(active != null && active.flag());
        this.setCode(Utils.hash(forCode()));
    }

    public String forCode() {
        return String.valueOf(System.currentTimeMillis());
    }
}
