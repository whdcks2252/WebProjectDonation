package com.donation.DonationWeb.repository;



import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.dto.MemberUpdateDto;

import java.util.List;

public interface MemberRepository  {
    Member save(Member member);

    void update(Long memberId, MemberUpdateDto updateParam);

    Member findById(Long id);

    List<Member> findAll();

    public void delete(long id);

}
