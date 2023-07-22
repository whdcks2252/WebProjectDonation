package com.donation.DonationWeb.comment.repository;


import com.donation.DonationWeb.comment.dto.UpdateCommentRequest;
import com.donation.DonationWeb.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);

    void delete(Comment comment);

    void update(Comment comment,UpdateCommentRequest updateCommentRequest);

    Optional<Comment> findById(Long commentId);
    Optional<Comment> findCommentWithPost(Long commentId);

}
