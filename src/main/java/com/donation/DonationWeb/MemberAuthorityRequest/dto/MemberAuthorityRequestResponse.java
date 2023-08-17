package com.donation.DonationWeb.MemberAuthorityRequest.dto;

import com.donation.DonationWeb.domain.MemberAuthorityRequest;
import com.donation.DonationWeb.domain.status.MemberAuthorityRequestProcess;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberAuthorityRequestResponse {
    private Long MemberAuthorityRequestId;
    private String organizationName;
    private String content;
    private MemberAuthorityRequestProcess memberAuthorityRequestProcess;
    private Long memberNum;
    private String memberId;
    private LocalDateTime createTime;
    private LocalDateTime lastUpdateTime;

    public static MemberAuthorityRequestResponse createInstance(MemberAuthorityRequest memberAuthorityRequest) {
        return new MemberAuthorityRequestResponse(memberAuthorityRequest.getId(),
                memberAuthorityRequest.getOrganizationName(),
                memberAuthorityRequest.getContent(),
                memberAuthorityRequest.getMemberAuthorityRequestProcess(),
                memberAuthorityRequest.getMember().getId(),
                memberAuthorityRequest.getMember().getMemberId(),
                memberAuthorityRequest.getCreateTime(),
                memberAuthorityRequest.getUpdateTime()
        );
    }

}
