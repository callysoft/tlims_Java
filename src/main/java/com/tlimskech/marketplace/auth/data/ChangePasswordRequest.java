package com.tlimskech.marketplace.auth.data;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ChangePasswordRequest implements Serializable {

    @NotNull
    private String username;
    @NotNull
    private String currentPassword;
    @NotNull
    private String password;
}
