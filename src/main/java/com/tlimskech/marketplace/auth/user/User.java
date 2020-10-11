package com.tlimskech.marketplace.auth.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.tlimskech.marketplace.auth.role.Role;
import com.tlimskech.marketplace.core.data.Active;
import com.tlimskech.marketplace.core.data.BaseEntity;
import com.tlimskech.marketplace.core.data.SearchRequest;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Table(name = "SEC_USER")
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Active
public class User extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String firstName;
    @NotBlank
    @Column(nullable = false)
    private String lastName;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String displayName;
    private Boolean verified;
    private String imageUrl;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public void encryptPassword() {
        PasswordEncoder ENCODER = new BCryptPasswordEncoder();
        this.password = ENCODER.encode(password);
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

    public static Map<String, String> generateAuthToken(String token, String authority, User user) {
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
//        tokenMap.put("created", String.valueOf(dateCreated));
        tokenMap.put("scope", authority);
//        tokenMap.put("email", user.getEmail());
        tokenMap.put("user", user.toJsonString(false));
        // tokenMap.put("refreshToken", refreshToken);
        return tokenMap;
    }
}
