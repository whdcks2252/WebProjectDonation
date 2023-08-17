package com.donation.DonationWeb.InterestVolunteerPost.service;

import com.donation.DonationWeb.domain.InterestVolunteerPost;

import java.util.Optional;

public interface InterestVolunteerPostService {

    public Optional<Boolean> interestPostPut(Long postId, Long loginId);
    public void interestPostDelete(Long postId,Long loginId);

    public InterestVolunteerPost findByLoginIdAndPostId(Long postId, Long loginId);
}
