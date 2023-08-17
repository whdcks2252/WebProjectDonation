package com.donation.DonationWeb.InterestVolunteerPost.repository;

import com.donation.DonationWeb.domain.InterestVolunteerPost;

import java.util.Optional;
public interface InterestVolunteerPostRepository {

    void save(InterestVolunteerPost interestVolunteerPost);

    void delete(InterestVolunteerPost interestVolunteerPost);

    Optional<InterestVolunteerPost> findByLoginIdAndPostId(Long postId, Long loginId);

}
