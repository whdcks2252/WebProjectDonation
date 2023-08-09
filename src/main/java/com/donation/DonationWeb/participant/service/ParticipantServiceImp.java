package com.donation.DonationWeb.participant.service;

import com.donation.DonationWeb.domain.*;
import com.donation.DonationWeb.exception.PostException;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.participant.repository.ParticipantRepository;
import com.donation.DonationWeb.volunteerPost.service.VolunteerPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ParticipantServiceImp implements ParticipantService{

    private final ParticipantRepository participantRepository;
    private final MemberService memberService;
    private final VolunteerPostService volunteerPostService;

    @Transactional
    @Override
    public Optional<Boolean> participantPut(Long volunteerPostId, Long loginId) {
        Member findMember = memberService.findById(loginId);
        VolunteerPost findPost = volunteerPostService.findById(volunteerPostId);

        if(checkParticipantAlready(volunteerPostId, loginId))
        {
            log.info("delete");
            Participant findParticipant = findByLoginIdAndPostId(volunteerPostId, loginId);//이미 참가자 찾기
            participantRepository.delete(findParticipant);//이미 참가자 삭제
            return Optional.empty();

        }
        else {
            log.info("create");
            Participant participant = ParticipantToEntity(findMember, findPost);
            participantRepository.save(participant);
            return Optional.of(true);
        }
    }

    @Override
    public Participant findByLoginIdAndPostId(Long volunteerPostId, Long loginId) {
        return participantRepository.findByLoginIdAndPostId(volunteerPostId, loginId).orElseThrow(()->new PostException("not found by volunteerPostId :"+volunteerPostId+" loginId : "+loginId));
    }

    private Boolean checkParticipantAlready(Long volunteerPostId, Long loginId) { //참가자 원래 있는지 확인
        return participantRepository.findByLoginIdAndPostId(volunteerPostId, loginId).isPresent();
    }

    private static Participant ParticipantToEntity(Member findMember, VolunteerPost findPost) {
        return Participant.builder().volunteerPost(findPost).member(findMember).build();
    }

    public List<Participant> findByIdPage(Long volunteerPostId,Integer page){
        return participantRepository.findByIdPage(volunteerPostId,page);
    }
}
