package com.donation.DonationWeb.member.service;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.member.dto.MemberUpdateDto;

import java.util.List;

public interface MemberService {

    Member save(Member member);

    void update(Long itemId, MemberUpdateDto updateParam);
    public void delete(long id);

    Member findById(Long memberId);
     List<Member> findMembers();
}
