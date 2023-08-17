package com.donation.DonationWeb.volunteerComment.repository;


import com.donation.DonationWeb.domain.VolunteerComment;
import com.donation.DonationWeb.volunteerComment.dto.UpdateVolunteerCommentRequest;

import java.util.Optional;

public interface VolunteerCommentRepository {
    VolunteerComment save(VolunteerComment volunteerComment);

    void delete(VolunteerComment volunteerComment);

    void update(VolunteerComment volunteerComment, UpdateVolunteerCommentRequest updateVolunteerCommentRequest);

    Optional<VolunteerComment> findById(Long commentId);

}
