package com.donation.DonationWeb.InterestVolunteerPost.service;

import com.donation.DonationWeb.InterestVolunteerPost.repository.InterestVolunteerPostRepository;
import com.donation.DonationWeb.domain.InterestVolunteerPost;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.VolunteerPost;
import com.donation.DonationWeb.exception.PostException;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.volunteerPost.service.VolunteerPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InterestVolunteerPostServiceImp implements InterestVolunteerPostService {
    private final InterestVolunteerPostRepository interestVolunteerPostRepository;
    private final MemberService memberService;
    private final VolunteerPostService volunteerPostService;
    @Override //인자값 주의
    public Optional<Boolean> interestPostPut(Long postId, Long loginId) {
        Member findMember = memberService.findById(loginId);
        VolunteerPost findPost = volunteerPostService.findById(postId);

        if(checkInterPostAlready(postId, loginId))
        {
            log.info("delete");
            InterestVolunteerPost findByInterestPost = findByLoginIdAndPostId(postId, loginId);//이미 등록되있는 관심게시물 찾기
            interestVolunteerPostRepository.delete(findByInterestPost);//이미 등록되있는 관심게시물 삭제
            return Optional.empty();

        }
        else {
            log.info("create");
            InterestVolunteerPost interestPost = InterestPostToEntity(findMember, findPost);
            interestVolunteerPostRepository.save(interestPost);
            return Optional.of(true);
        }



    }

    @Override
    public void interestPostDelete(Long postId, Long loginId) {

        InterestVolunteerPost findByInterestPost = findByLoginIdAndPostId(postId, loginId);
        interestVolunteerPostRepository.delete(findByInterestPost);


    }
    @Override
    public InterestVolunteerPost findByLoginIdAndPostId(Long postId, Long loginId) {
        return interestVolunteerPostRepository.findByLoginIdAndPostId(postId, loginId).orElseThrow(()->new PostException("not found by postId :"+postId+" loginId : "+loginId));
    }

    private Boolean checkInterPostAlready(Long postId, Long loginId) { //관심게시물 원래 있는지 확인
        return interestVolunteerPostRepository.findByLoginIdAndPostId(postId, loginId).isPresent();
    }


    private static InterestVolunteerPost InterestPostToEntity(Member findMember, VolunteerPost findPost) {
        return InterestVolunteerPost.builder().volunteerPost(findPost).member(findMember).build();
    }



}
