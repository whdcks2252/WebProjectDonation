package com.donation.DonationWeb.login.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginMemberRequest {

    @NotBlank
    private String loginId;
    @NotBlank
    private String password;

    public LoginMemberRequest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

}
