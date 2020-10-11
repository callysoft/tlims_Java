package com.tlimskech.marketplace.auth.data;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ResetPasswordRequest {
    @NotBlank(message = "Token is required")
    private String token;
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
}
