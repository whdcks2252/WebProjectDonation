package com.donation.DonationWeb.MemberAuthorityRequest.dto;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.MemberAuthorityRequest;
import com.donation.DonationWeb.domain.status.MemberAuthorityRequestProcess;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberAuthorityRequestDto {
    @NotBlank
    private String organizationName;
    @NotBlank
    private String content;

    public MemberAuthorityRequest toEntity(MemberAuthorityRequestDto memberAuthorityRequestDto, Member member) {
        return  MemberAuthorityRequest
                .builder()
                .organizationName(memberAuthorityRequestDto.organizationName)
                .content(memberAuthorityRequestDto.content)
                .member(member)
                .memberAuthorityRequestProcess(MemberAuthorityRequestProcess.WAIT)
                .build();


    }

}
