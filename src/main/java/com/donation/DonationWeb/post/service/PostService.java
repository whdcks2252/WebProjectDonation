package com.donation.DonationWeb.post.service;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.post.dto.DeletePostRequest;
import com.donation.DonationWeb.post.dto.PostResponse;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import com.donation.DonationWeb.post.dto.AddPostRequest;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public Post savePost(AddPostRequest addPostRequest,Long id);

    public List<Post> findAll();

    public Post findById(Long postId);
    public List<Post> findByPage(Integer page);

    public List<Post> findByCategry(String categoryName,Integer page);

    public void updatePost( UpdatePostRequest upPost,Long loginId) ;

    public void delete(DeletePostRequest deletePostRequest,Long LoginId);
}
