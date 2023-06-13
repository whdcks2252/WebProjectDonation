package com.donation.DonationWeb.post.repository;

import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    void update(Long postId, UpdatePostRequest updateParam);

    void delete(Long postId);
    Optional<Post> findById(Long postId);

    List<Post> findAll();

}
