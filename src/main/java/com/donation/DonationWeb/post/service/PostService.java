package com.donation.DonationWeb.post.service;

import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import com.donation.DonationWeb.post.dto.addPostRequest;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public Post savePost(addPostRequest addPostRequest);

    public List<Post> findAll();

    public Optional<Post> findById(Long postId);

    public void updatePost(Long postId, UpdatePostRequest upPost) ;

    public void delete(Long postId);
}
