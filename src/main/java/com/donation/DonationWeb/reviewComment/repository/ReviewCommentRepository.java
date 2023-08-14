package com.donation.DonationWeb.reviewComment.repository;


import com.donation.DonationWeb.domain.ReviewComment;
import com.donation.DonationWeb.reviewComment.dto.UpdateReviewCommentRequest;

import java.util.Optional;

public interface ReviewCommentRepository {
    ReviewComment save(ReviewComment reviewComment);

    void delete(ReviewComment reviewComment);

    void update(ReviewComment reviewComment, UpdateReviewCommentRequest updateReviewCommentRequest);

    Optional<ReviewComment> findById(Long commentId);

}
