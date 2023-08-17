package com.donation.DonationWeb.MemberAuthorityRequest.repository;

import com.donation.DonationWeb.domain.MemberAuthorityRequest;
import com.donation.DonationWeb.domain.Post;

import java.util.List;
import java.util.Optional;

public interface MemberAuthorityRequestRepository {

    public MemberAuthorityRequest save(MemberAuthorityRequest memberAuthorityRequest);

    public Optional<MemberAuthorityRequest> findById(Long id);

    public Optional<MemberAuthorityRequest> findByMemberId(Long memberId);
    List<MemberAuthorityRequest> findByPage(Integer page);
}
