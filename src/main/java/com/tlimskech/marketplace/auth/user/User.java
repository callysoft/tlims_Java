package com.tlimskech.marketplace.auth.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.tlimskech.marketplace.auth.role.Role;
import com.tlimskech.marketplace.core.data.BaseEntity;
import com.tlimskech.marketplace.core.data.SearchRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Table(name = "SEC_USER")
@Entity
@Data
public class User extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String firstName;
    private String lastName;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String password;
    @NotBlank
    @Column(nullable = false)
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String encryptPassword() {
        PasswordEncoder ENCODER = new BCryptPasswordEncoder();
        return this.password = ENCODER.encode(password);
    }

    public BooleanExpression searchPredicate(SearchRequest request) {
        QUser qUser = QUser.user;
        if (StringUtils.isEmpty(request.getSearchTerm())) return qUser.isNotNull();
        return qUser.email.containsIgnoreCase(request.getSearchTerm())
                .or(qUser.firstName.containsIgnoreCase(request.getSearchTerm()))
                .or(qUser.lastName.containsIgnoreCase(request.getSearchTerm()))
                .or(qUser.phoneNumber.containsIgnoreCase(request.getSearchTerm()))
                .or(qUser.createdDate.stringValue().containsIgnoreCase(request.getSearchTerm()));
    }
}
