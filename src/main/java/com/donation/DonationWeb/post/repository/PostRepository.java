package com.donation.DonationWeb.post.repository;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.post.dto.PostResponse;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    void update(UpdatePostRequest updateParam,Category category);


    void delete(Long postId);

    Optional<Post> findById(Long postId);
    List<Post> findByMemberId(String memberId);
    List<Post> findByPage(Integer page);
    List<Post> findByCategry(Long categoryId,Integer page);

    List<Post> findAll();

}
