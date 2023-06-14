package com.donation.DonationWeb.member.service;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.ServiceAgreement;
import com.donation.DonationWeb.member.dto.AddMemberRequest;
import com.donation.DonationWeb.member.dto.MemberUpdateDto;
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

    private final MemberRepositoryImp memberRepository;

    @Transactional
    @Override
    public Member save(AddMemberRequest addMemberRequest, ServiceAgreement sva){
        return memberRepository.save(addMemberRequest.toEntity(sva));
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
    public Member findById(Long memberId) {return memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("not found : " + memberId));}



}
