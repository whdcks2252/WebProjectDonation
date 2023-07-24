package com.donation.DonationWeb.member.service;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.ServiceAgreement;
import com.donation.DonationWeb.login.dto.LoginMemberRequest;
import com.donation.DonationWeb.member.dto.AddMemberRequest;
import com.donation.DonationWeb.member.dto.IdCheckRequest;
import com.donation.DonationWeb.member.dto.MemberUpdateDto;
import com.donation.DonationWeb.member.dto.NicknameCheckRequest;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    Member save(AddMemberRequest addMemberRequest);

    public void update(Long memberId, MemberUpdateDto updateParam);
    public void delete(Long memberId);

    Member findById(Long memberId);
   Member findUserPosts(Long memberId,Integer page);
    Member findUserInterestPosts(Long memberId,Integer page);

    List<Member> findAll();

    Optional<Member> findByMemberIdAndPassword(String memberId,String password);

    String idCheck(IdCheckRequest id);
    String nickNameCheck(NicknameCheckRequest nickName);


}
