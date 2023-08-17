package com.donation.DonationWeb.reviewComment.service;

import com.donation.DonationWeb.domain.ReviewComment;
import com.donation.DonationWeb.reviewComment.dto.CreateReviewCommentRequest;
import com.donation.DonationWeb.reviewComment.dto.UpdateReviewCommentRequest;

public interface ReviewCommentService {
    ReviewComment save(CreateReviewCommentRequest createReviewCommentRequest, Long loginId, Long postId);

    void delete(Long commentId,Long loginId);

    void update(UpdateReviewCommentRequest updateReviewCommentRequest, Long commentId, Long loginId);

    ReviewComment findById(Long commentId);
}
