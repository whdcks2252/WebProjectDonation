package com.donation.DonationWeb.reviewPost.repository;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.ReviewPost;
import com.donation.DonationWeb.reviewPost.dto.UpdateReviewPostRequest;

import java.util.List;
import java.util.Optional;

public interface ReviewPostRepository {
    ReviewPost save(ReviewPost reviewPost);

    Optional<ReviewPost> findById(Long reviewPostId);

    Optional<ReviewPost> findByIdLeftJoin(Long reviewPostId);

    List<ReviewPost> findByPage(Integer page);

    List<ReviewPost> findByCategory(Long categoryId, Integer page);

    void update(UpdateReviewPostRequest request, ReviewPost reviewPost, Category category, Post post);

    void delete(ReviewPost reviewPost);
}
