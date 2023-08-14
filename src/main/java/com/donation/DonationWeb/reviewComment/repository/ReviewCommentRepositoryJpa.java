package com.donation.DonationWeb.reviewComment.repository;

import com.donation.DonationWeb.comment.dto.UpdateCommentRequest;
import com.donation.DonationWeb.domain.Comment;
import com.donation.DonationWeb.domain.ReviewComment;
import com.donation.DonationWeb.reviewComment.dto.UpdateReviewCommentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReviewCommentRepositoryJpa implements ReviewCommentRepository {

    private final EntityManager em;
    @Override
    public ReviewComment save(ReviewComment reviewComment) {
        em.persist(reviewComment);
        return reviewComment;
    }

    @Override
    public void delete(ReviewComment reviewComment) {
        em.remove(reviewComment);

    }

    @Override
    public void update(ReviewComment reviewComment, UpdateReviewCommentRequest updateReviewCommentRequest) {

            reviewComment.updateComment(updateReviewCommentRequest.getContent());
    }



    @Override
    public Optional<ReviewComment> findById(Long commentId) {
        return Optional.ofNullable(em.find(ReviewComment.class, commentId));

    }
}
