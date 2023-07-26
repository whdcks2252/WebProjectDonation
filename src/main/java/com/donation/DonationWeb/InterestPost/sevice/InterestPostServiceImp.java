package com.donation.DonationWeb.InterestPost.sevice;

import com.donation.DonationWeb.InterestPost.repository.InterestPostRepository;
import com.donation.DonationWeb.domain.InterestPost;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.exception.PostException;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InterestPostServiceImp implements InterestPostService {
    private final InterestPostRepository interestPostRepository;
    private final MemberService memberService;
    private final PostService postService;
    @Override //인자값 주의
    public Optional<Boolean> interestPostPut(Long postId, Long loginId) {
        Member findMember = memberService.findById(loginId);
        Post findPost = postService.findById(postId);

        if(checkInterPostAlready(postId, loginId))
        {
            log.info("delete");
            InterestPost findByInterestPost = findByLoginIdAndPostId(postId, loginId);//이미 등록되있는 관심게시물 찾기
            interestPostRepository.delete(findByInterestPost);//이미 등록되있는 관심게시물 삭제
            return Optional.empty();

        }
        else {
            log.info("create");
            InterestPost interestPost = InterestPostToEntity(findMember, findPost);
            interestPostRepository.save(interestPost);
            return Optional.of(true);
        }



    }

    @Override
    public void interestPostDelete(Long postId, Long loginId) {

        InterestPost findByInterestPost = findByLoginIdAndPostId(postId, loginId);
        interestPostRepository.delete(findByInterestPost);


    }
    @Override
    public InterestPost findByLoginIdAndPostId(Long postId, Long loginId) {
        return interestPostRepository.findbyLoginIdAndPostId(postId, loginId).orElseThrow(()->new PostException("not found by postId :"+postId+" loginId : "+loginId));
    }

    private Boolean checkInterPostAlready(Long postId, Long loginId) { //관심게시물 원래 있는지 확인
        return interestPostRepository.findbyLoginIdAndPostId(postId, loginId).isPresent();
    }


    private static InterestPost InterestPostToEntity(Member findMember, Post findPost) {
        return InterestPost.builder().post(findPost).member(findMember).build();
    }



}
