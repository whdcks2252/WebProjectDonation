package com.donation.DonationWeb.member.repository;



import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.member.dto.MemberUpdateDto;

import java.util.List;
import java.util.Optional;

public interface MemberRepository  {
    Member save(Member member);

    void update(Long memberId, MemberUpdateDto updateParam);

    Optional<Member> findById(Long memberId);

    List<Member> findAll();

    public void delete(Long memberId);

}
