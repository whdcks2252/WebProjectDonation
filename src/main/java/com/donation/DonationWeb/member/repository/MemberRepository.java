package com.donation.DonationWeb.member.repository;



import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.login.dto.LoginMemberRequest;
import com.donation.DonationWeb.member.dto.IdCheckRequest;
import com.donation.DonationWeb.member.dto.MemberUpdateDto;
import com.donation.DonationWeb.member.dto.NicknameCheckRequest;

import java.util.List;
import java.util.Optional;

public interface MemberRepository  {
    Member save(Member member);

    void update(Long memberId, MemberUpdateDto updateParam);

    Optional<Member> findById(Long memberId);

    List<Member> findAll();

     void delete(Long memberId);

    Optional<Member> findMemberIDAndPassword(LoginMemberRequest loginMemberRequest);

    Optional<Member> idCheck(String id);
    Optional<Member> nickNameCheck(String id);

}
