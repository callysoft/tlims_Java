package com.tlimskech.marketplace.auth.token;

import com.tlimskech.marketplace.core.data.Data;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author goodluck
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sec_token")
@SequenceGenerator(name = "defaultSequenceGen", initialValue = 1000, sequenceName = "SEC_TOKENS_SEQ", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
public class Token extends Data {

    @Id
    @SequenceGenerator(name = "defaultSequenceGen", sequenceName = "HIBERNATE_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "defaultSequenceGen")
    private Long id;

    @NotBlank(message = "core.token.token.empty")
    @Size(message = "core.token.token.size", max = 255)
    @Column(name = "token", nullable = false)
    private String token;

    @Column(name = "data_id")
    private Long dataId;

    @Column(name = "data_ref")
    private String dataRef;

    @NotNull(message = "security.token.createdate.required")
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "used_date")
    private Date usedDate;

    @Column(name = "expiry_date")
    private LocalDateTime expiryDate;

    @Column(name = "token_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.SOFT_TOKEN;

    @Column(name = "used")
    private Boolean used = false;

    private Boolean isExpired;


    public void invalidate() {
        this.usedDate = new Date();
        this.used = true;
    }

    public Boolean isExpired() {
        return (this.expiryDate != null) && LocalDateTime.now().isAfter(this.expiryDate);
    }
}
