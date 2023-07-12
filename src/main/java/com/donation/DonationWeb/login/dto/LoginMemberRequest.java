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
    private String passWord;

    public LoginMemberRequest(String loginId, String passWord) {
        this.loginId = loginId;
        this.passWord = passWord;
    }

}
