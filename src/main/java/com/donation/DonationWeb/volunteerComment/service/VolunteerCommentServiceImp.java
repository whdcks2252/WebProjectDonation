package com.donation.DonationWeb.volunteerComment.service;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.VolunteerComment;
import com.donation.DonationWeb.domain.VolunteerPost;
import com.donation.DonationWeb.exception.CommentException;
import com.donation.DonationWeb.exception.PostException;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.volunteerComment.dto.CreateVolunteerCommentRequest;
import com.donation.DonationWeb.volunteerComment.dto.UpdateVolunteerCommentRequest;
import com.donation.DonationWeb.volunteerComment.repository.VolunteerCommentRepository;
import com.donation.DonationWeb.volunteerPost.service.VolunteerPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class VolunteerCommentServiceImp implements VolunteerCommentService {
    private final VolunteerCommentRepository volunteerCommentRepository;
    private final MemberService memberService;
    private final VolunteerPostService volunteerPostService;

    @Transactional
    @Override
    public VolunteerComment save(CreateVolunteerCommentRequest createVolunteerCommentRequest, Long loginId, Long postId) {
        Member findMember = memberService.findById(loginId);
        VolunteerPost findPostWithMember = volunteerPostService.findById(postId);

        VolunteerComment createComment = createVolunteerCommentRequest.toEntity(findPostWithMember, findMember);

        return volunteerCommentRepository.save(createComment);
    }

    @Transactional
    @Override
    public void delete(Long commentId,Long loginId) {
        VolunteerComment findComment = findById(commentId);

        if(commentAndMemberCheck(findComment.getMember().getId(),loginId))
        {
            volunteerCommentRepository.delete(findComment);

        }
        else
        {
            throw   new PostException("댓글 삭제가 실패 햐엿습니다");
        }

    }
    @Transactional
    @Override
    public void update(UpdateVolunteerCommentRequest updateVolunteerCommentRequest, Long commentId, Long loginId) {

        VolunteerComment findComment = findById(commentId);

        if (commentAndMemberCheck(findComment.getMember().getId(), loginId)) {
            volunteerCommentRepository.update(findComment,updateVolunteerCommentRequest);
        }

    }

    @Override
    public VolunteerComment findById(Long commentId) {

        return volunteerCommentRepository.findById(commentId).orElseThrow(() -> new CommentException(" not found commentId : " + commentId));
    }

    private Boolean commentAndMemberCheck(long PostId,long MemberId) {
        if (PostId == MemberId) {
            return true;
        }
        else
            throw new CommentException("틀린 댓글 요청입니다.");

    }
}
