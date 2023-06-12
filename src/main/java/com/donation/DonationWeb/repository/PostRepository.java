package com.donation.DonationWeb.repository;

import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.dto.UpdatePostRequest;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post item);

    void update(Long itemId, UpdatePostRequest updateParam);

    Optional<Post> findById(Long id);

    List<Post> findAll();

}
