package com.donation.DonationWeb.comment.service;

import com.donation.DonationWeb.comment.dto.AddCommentRequest;
import com.donation.DonationWeb.comment.dto.UpdateCommentRequest;
import com.donation.DonationWeb.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment save(AddCommentRequest addComment, Long loginId, Long postId);

    void delete(Long commentId,Long loginId);

    void update(UpdateCommentRequest updateComment, Long commentId,Long loginId);

    Comment findById(Long commentId);
}
