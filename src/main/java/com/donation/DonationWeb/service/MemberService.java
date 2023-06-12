package com.donation.DonationWeb.service;

import com.donation.DonationWeb.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Member save(Member member);

    void update(Long itemId, MemberUpdateDto updateParam);
    public void delete(long id);

    Member findById(Long memberId);
     List<Member> findMembers();
}
