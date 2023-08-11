package com.donation.DonationWeb.adminUser.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginAdminUserRequest {

    @NotBlank
    private String loginId;
    @NotBlank
    private String password;

    public LoginAdminUserRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
