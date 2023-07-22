package com.donation.DonationWeb.member.repository;



import com.donation.DonationWeb.domain.InterestPost;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
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
    Optional<Member> findUserPosts(Long memberId, Integer page);
    Optional<Member> findUserInterestPosts(Long memberId, Integer page);

    Optional<Member> findMemberIDAndPassword(LoginMemberRequest loginMemberRequest);

    List<Member> findAll();

     void delete(Long memberId);

    Optional<Member> idCheck(String id);
    Optional<Member> nickNameCheck(String id);

}
