package com.donation.DonationWeb.MemberAuthorityRequest.service;

import com.donation.DonationWeb.MemberAuthorityRequest.dto.MemberAuthorityRequestDto;
import com.donation.DonationWeb.MemberAuthorityRequest.repository.MemberAuthorityRequestRepository;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.MemberAuthorityRequest;
import com.donation.DonationWeb.domain.status.MemberAuthorityRequestProcess;
import com.donation.DonationWeb.domain.status.MemberRole;
import com.donation.DonationWeb.exception.MemberAuthorityRequestException;
import com.donation.DonationWeb.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberAuthorityRequestServiceImp implements MemberAuthorityRequestService{
    private final MemberAuthorityRequestRepository memberAuthorityRequestRepository;
    private final MemberService memberService;

    @Transactional
    @Override
    public MemberAuthorityRequest saveMemberAuthorityRequest(MemberAuthorityRequestDto memberAuthorityRequestDto, Long loginId) {
        Member findMember = memberService.findById(loginId);
        checkDuplication(findMember);
        checkMemberRole(findMember);
        return memberAuthorityRequestRepository.save(memberAuthorityRequestDto.toEntity(memberAuthorityRequestDto, findMember));
    }

    @Override
    public MemberAuthorityRequest findById(Long id) {
        return memberAuthorityRequestRepository.findById(id).orElseThrow(() -> new MemberAuthorityRequestException("요청서를 찾을수 없습니다 id : " + id));
    }

    @Override
    public List<MemberAuthorityRequest> findByPage(Integer page) {
       return memberAuthorityRequestRepository.findByPage(page);
    }

    private void checkDuplication(Member member) { //요청서 중복 확인
        Optional<MemberAuthorityRequest> byMemberId = memberAuthorityRequestRepository.findByMemberId(member.getId());
        if(byMemberId.isPresent()){
           throw new MemberAuthorityRequestException("이미 요청서가 작성되었습니다");
       }

    }
    private void checkMemberRole(Member findMember) {//멤버의 권한 확인 비권한자만 신청 가능
        if(findMember.getMemberRole().equals(MemberRole.AUTHORIZED)){
            throw new MemberAuthorityRequestException("이미 권한이 있는 사용자 입니다");
        }
    }

}
