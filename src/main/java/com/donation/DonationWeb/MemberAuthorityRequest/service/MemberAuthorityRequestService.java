package com.donation.DonationWeb.MemberAuthorityRequest.service;

import com.donation.DonationWeb.MemberAuthorityRequest.dto.MemberAuthorityRequestDto;
import com.donation.DonationWeb.domain.MemberAuthorityRequest;

import java.util.List;

public interface MemberAuthorityRequestService {
    public MemberAuthorityRequest saveMemberAuthorityRequest(MemberAuthorityRequestDto memberAuthorityRequestDto, Long loginId);

    public MemberAuthorityRequest findById(Long id);
    List<MemberAuthorityRequest> findByPage(Integer page);
}
