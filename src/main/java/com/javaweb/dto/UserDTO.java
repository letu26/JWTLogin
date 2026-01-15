package com.javaweb.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "RetypePassword is required")
    private String retypePassword;

    @NotNull(message = "Role id is required")
    private Long roleId;
}

