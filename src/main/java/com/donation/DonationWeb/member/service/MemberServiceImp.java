package com.donation.DonationWeb.member.service;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.ServiceAgreement;
import com.donation.DonationWeb.exception.UserException;
import com.donation.DonationWeb.login.dto.LoginMemberRequest;
import com.donation.DonationWeb.member.dto.AddMemberRequest;
import com.donation.DonationWeb.member.dto.IdCheckRequest;
import com.donation.DonationWeb.member.dto.MemberUpdateDto;
import com.donation.DonationWeb.member.dto.NicknameCheckRequest;
import com.donation.DonationWeb.member.repository.MemberRepository;
import com.donation.DonationWeb.member.repository.MemberRepositoryImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService{

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Member save(AddMemberRequest addMemberRequest){
        return memberRepository.save(addMemberRequest.toEntity());
    }

    /**
     * 영속성 컨텍스트가 자동 변경
     */
    @Transactional
    @Override
    public void update(Long itemId, MemberUpdateDto updateParam) {
            memberRepository.update(itemId,updateParam);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        memberRepository.delete(id);
    }

    @Override
    public List<Member> findAll() {return memberRepository.findAll();}

    @Override
    public Member findById(Long memberId) {return memberRepository.findById(memberId).orElseThrow(() -> new UserException("not found : " + memberId));}

    @Override
    public Member findByMemberId(String memberName) {
       return memberRepository.findByMemberId(memberName).orElseThrow(()->new UserException("not found:"+memberName));

    }

    @Override
    public Optional<Member> findMemberIDAndPassword(LoginMemberRequest loginMemberRequest) {
        Optional<Member> memberIDAndPassword = memberRepository.findMemberIDAndPassword(loginMemberRequest);

        if (!memberIDAndPassword.isPresent()) {
            return Optional.empty();
        }


        return memberIDAndPassword;
    }

    @Override
    public String idCheck(IdCheckRequest id) {
        Optional<Member> idCheck = memberRepository.idCheck(id.getId());
        if (idCheck.isPresent())
        {
            return "존재하는 아이디 입니다.";
        }
        else {
            return "사용가능한 아이디 입니다.";
        }
    }
    @Override
    public String nickNameCheck(NicknameCheckRequest nickName) {
        Optional<Member> nickNameCheck = memberRepository.nickNameCheck(nickName.getNickName());
        if (nickNameCheck.isPresent())
        {
            return "존재하는 닉네임 입니다.";
        }
        else {
            return "사용가능한 닉네임 입니다.";
        }
    }
}