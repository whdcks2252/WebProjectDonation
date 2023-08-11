package com.donation.DonationWeb.reviewPost.service;

import com.donation.DonationWeb.domain.ReviewPost;
import com.donation.DonationWeb.domain.VolunteerPost;
import com.donation.DonationWeb.reviewPost.dto.CreateReviewPostRequest;
import com.donation.DonationWeb.reviewPost.dto.UpdateReviewPostRequest;
import com.donation.DonationWeb.volunteerPost.dto.CreateVolunteerPostRequest;
import com.donation.DonationWeb.volunteerPost.dto.UpdateVolunteerPostRequest;

import java.util.List;

public interface ReviewPostService {
    public ReviewPost savePost(CreateReviewPostRequest request, Long id);

    public ReviewPost findById(Long reviewPostId);

    public ReviewPost findByIdLeftJoin(Long reviewPostId);

    public List<ReviewPost> findByPage(Integer page);

    public List<ReviewPost> findByCategory(String categoryName, Integer page);

    public ReviewPost findByPostTitle(String postTitle);

    public void updatePost(UpdateReviewPostRequest request, Long reviewPostId, Long loginId);

    public void delete(Long reviewPostId, Long loginId);
}
