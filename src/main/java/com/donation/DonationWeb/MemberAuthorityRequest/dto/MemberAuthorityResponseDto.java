package com.donation.DonationWeb.MemberAuthorityRequest.dto;

import com.donation.DonationWeb.domain.MemberAuthorityRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberAuthorityResponseDto {
    private Long MemberAuthorityRequestId;
    private String organizationName;
    private String content;
    private Long memberNum;
    private String memberId;

    public static MemberAuthorityResponseDto createInstance(MemberAuthorityRequest memberAuthorityRequest) {
        return new MemberAuthorityResponseDto(memberAuthorityRequest.getId(),
                memberAuthorityRequest.getOrganizationName(),
                memberAuthorityRequest.getContent(),
                memberAuthorityRequest.getMember().getId(),
                memberAuthorityRequest.getMember().getMemberId()
        );
    }

}
