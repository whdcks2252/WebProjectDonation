package com.donation.DonationWeb.comment.service;

import com.donation.DonationWeb.comment.dto.AddCommentRequest;
import com.donation.DonationWeb.comment.dto.UpdateCommentRequest;
import com.donation.DonationWeb.comment.repository.CommentRepository;
import com.donation.DonationWeb.domain.Comment;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.exception.CommentException;
import com.donation.DonationWeb.exception.PostException;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CommentServiceImp implements CommentService {
    private final CommentRepository commentRepository;
    private final MemberService memberService;
    private final PostService postService;
    @Transactional
    @Override
    public Comment save(AddCommentRequest addComment, Long loginId, Long postId) {
        Member findMember = memberService.findById(loginId);
        Post findPostWithMember = postService.findById(postId);

        Comment createComment = addComment.toEntity(findPostWithMember, findMember);

        return commentRepository.save(createComment);
    }
    @Transactional
    @Override
    public Comment createNestedComment(AddCommentRequest addComment, Long commentId, Long loginId) {
        //댓글작성할 유저 찾기
        Member findMember = memberService.findById(loginId);
        //부모 댓글 찾기
        Comment findParentComment = commentRepository.findCommentWithPost(commentId).orElseThrow(() -> new CommentException(" not found commentId : " + commentId));
        //대댓글 생성
        Comment createComment = addComment.toEntity(findParentComment.getPost(),findMember);
      //  createComment.setParentComment(findParentComment);

        return commentRepository.save(createComment);
    }

    @Transactional
    @Override
    public void delete(Long commentId,Long loginId) {
        Comment findComment = findById(commentId);

        if(commentAndMemberCheck(findComment.getMember().getId(),loginId))
        {
            commentRepository.delete(findComment);

        }
        else
        {
            throw   new PostException("댓글 삭제가 실패 햐엿습니다");
        }

    }
    @Transactional
    @Override
    public void update(UpdateCommentRequest updateComment,Long commentId,Long loginId) {

        Comment findComment = findById(commentId);

        if (commentAndMemberCheck(findComment.getMember().getId(), loginId)) {
            commentRepository.update(findComment,updateComment);
        }

    }

    @Override
    public Comment findById(Long commentId) {

        return commentRepository.findById(commentId).orElseThrow(() -> new CommentException(" not found commentId : " + commentId));
    }

    private Boolean commentAndMemberCheck(long PostId,long MemberId) {
        if (PostId == MemberId) {
            return true;
        }
        else
            throw new CommentException("틀린 댓글 요청입니다.");

    }
}
