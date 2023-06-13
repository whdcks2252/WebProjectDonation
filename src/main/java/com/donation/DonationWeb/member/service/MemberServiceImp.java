package com.donation.DonationWeb.member.service;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.member.dto.MemberUpdateDto;
import com.donation.DonationWeb.member.repository.MemberRepositoryImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImp implements MemberService{

    private final MemberRepositoryImp memberRepository;

    @Transactional
    @Override
    public Member save(Member member){
        return memberRepository.save(member);
    }

    @Override
    public void update(Long itemId, MemberUpdateDto updateParam) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<Member> findMembers() {return memberRepository.findAll();}

    @Override
    public Member findById(Long memberId) {return memberRepository.findOne(memberId);}

    /**
     * 영속성 컨텍스트가 자동 변경
     */
    @Transactional
    public void updatePost(Long id,String content) {
        Member member = memberRepository.findOne(id);
    }

}
