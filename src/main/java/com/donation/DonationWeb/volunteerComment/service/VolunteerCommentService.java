package com.donation.DonationWeb.volunteerComment.service;

import com.donation.DonationWeb.domain.ReviewComment;
import com.donation.DonationWeb.domain.VolunteerComment;
import com.donation.DonationWeb.reviewComment.dto.CreateReviewCommentRequest;
import com.donation.DonationWeb.reviewComment.dto.UpdateReviewCommentRequest;
import com.donation.DonationWeb.volunteerComment.dto.CreateVolunteerCommentRequest;
import com.donation.DonationWeb.volunteerComment.dto.UpdateVolunteerCommentRequest;

public interface VolunteerCommentService {
    VolunteerComment save(CreateVolunteerCommentRequest createVolunteerCommentRequest, Long loginId, Long postId);

    void delete(Long commentId,Long loginId);

    void update(UpdateVolunteerCommentRequest updateVolunteerCommentRequest, Long commentId, Long loginId);

    VolunteerComment findById(Long commentId);
}
