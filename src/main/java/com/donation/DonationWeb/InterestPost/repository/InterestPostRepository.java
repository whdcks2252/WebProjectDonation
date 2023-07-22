package com.donation.DonationWeb.InterestPost.repository;
import com.donation.DonationWeb.domain.InterestPost;
import java.util.Optional;
public interface InterestPostRepository {

    void save(InterestPost interestPost);

    void delete(InterestPost interestPost);

    Optional<InterestPost> findbyLoginIdAndPostId(Long postId, Long loginId);

}
