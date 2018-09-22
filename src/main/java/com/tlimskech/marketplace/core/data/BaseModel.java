package com.tlimskech.marketplace.core.data;

import com.tlimskech.marketplace.core.util.Utils;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
@lombok.Data
public class BaseModel extends Data implements ICode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @UpdateTimestamp
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
        //set active flag based on annotation
        Active active = this.getClass().getDeclaredAnnotation(Active.class);
        this.setStatus(active != null && active.flag());
        setCode(Utils.hash(forCode()));
    }

    public String forCode() {
        return String.valueOf(System.currentTimeMillis());
    }
}
