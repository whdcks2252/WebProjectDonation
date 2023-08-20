package com.donation.DonationWeb.adminUser.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ManageMemberAuthorityRequest {

    @NotBlank
    private String memberAuthorityRequestProcess;

    public ManageMemberAuthorityRequest(String memberAuthorityRequestProcess) {
        this.memberAuthorityRequestProcess = memberAuthorityRequestProcess;
    }
}
