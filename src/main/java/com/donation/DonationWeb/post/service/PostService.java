package com.donation.DonationWeb.post.service;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import com.donation.DonationWeb.post.dto.AddPostRequest;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public Post savePost(AddPostRequest addPostRequest, Category category);

    public List<Post> findAll();

    public Post findById(Long postId);

    public void updatePost(Long postId, UpdatePostRequest upPost) ;

    public void delete(Long postId);
}
