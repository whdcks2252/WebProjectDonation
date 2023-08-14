package com.donation.DonationWeb.reviewComment.service;

import com.donation.DonationWeb.comment.dto.AddCommentRequest;
import com.donation.DonationWeb.comment.dto.UpdateCommentRequest;
import com.donation.DonationWeb.comment.repository.CommentRepository;
import com.donation.DonationWeb.domain.*;
import com.donation.DonationWeb.exception.CommentException;
import com.donation.DonationWeb.exception.PostException;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.post.service.PostService;
import com.donation.DonationWeb.reviewComment.dto.CreateReviewCommentRequest;
import com.donation.DonationWeb.reviewComment.dto.UpdateReviewCommentRequest;
import com.donation.DonationWeb.reviewComment.repository.ReviewCommentRepository;
import com.donation.DonationWeb.reviewPost.service.ReviewPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReviewCommentServiceImp implements ReviewCommentService {
    private final ReviewCommentRepository reviewCommentRepository;
    private final MemberService memberService;
    private final ReviewPostService reviewPostService;
    @Transactional
    @Override
    public ReviewComment save(CreateReviewCommentRequest createReviewCommentRequest, Long loginId, Long postId) {
        Member findMember = memberService.findById(loginId);
        ReviewPost findPostWithMember = reviewPostService.findById(postId);

        ReviewComment createComment = createReviewCommentRequest.toEntity(findPostWithMember, findMember);

        return reviewCommentRepository.save(createComment);
    }

    @Transactional
    @Override
    public void delete(Long commentId,Long loginId) {
        ReviewComment findComment = findById(commentId);

        if(commentAndMemberCheck(findComment.getMember().getId(),loginId))
        {
            reviewCommentRepository.delete(findComment);

        }
        else
        {
            throw   new PostException("댓글 삭제가 실패 햐엿습니다");
        }

    }
    @Transactional
    @Override
    public void update(UpdateReviewCommentRequest updateReviewCommentRequest, Long commentId, Long loginId) {

        ReviewComment findComment = findById(commentId);

        if (commentAndMemberCheck(findComment.getMember().getId(), loginId)) {
            reviewCommentRepository.update(findComment,updateReviewCommentRequest);
        }

    }

    @Override
    public ReviewComment findById(Long commentId) {

        return reviewCommentRepository.findById(commentId).orElseThrow(() -> new CommentException(" not found commentId : " + commentId));
    }

    private Boolean commentAndMemberCheck(long PostId,long MemberId) {
        if (PostId == MemberId) {
            return true;
        }
        else
            throw new CommentException("틀린 댓글 요청입니다.");

    }
}
