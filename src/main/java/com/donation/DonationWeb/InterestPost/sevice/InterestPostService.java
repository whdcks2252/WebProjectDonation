package com.donation.DonationWeb.InterestPost.sevice;

import com.donation.DonationWeb.domain.InterestPost;

import java.util.Optional;

public interface InterestPostService {

    public Optional<Boolean> interestPostPut(Long postId, Long loginId);
    public void interestPostDelete(Long postId,Long loginId);

    public InterestPost findByLoginIdAndPostId(Long postId, Long loginId);
}
