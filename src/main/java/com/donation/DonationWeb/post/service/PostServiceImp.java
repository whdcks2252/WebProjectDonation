package com.donation.DonationWeb.post.service;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import com.donation.DonationWeb.post.dto.AddPostRequest;
import com.donation.DonationWeb.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post savePost(AddPostRequest addPostRequest, Category category){ return postRepository.save(addPostRequest.toEntity(category));
    }

    public List<Post> findAll() {return postRepository.findAll();}

    public Post findById(Long postId) {return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("not found : " + postId));}

    /**
     * 영속성 컨텍스트가 자동 변경
     */
    @Transactional
    public void updatePost(Long postId, UpdatePostRequest upPost) {
        postRepository.update(postId,upPost);


    }
    @Transactional
    @Override
    public void delete(Long postId) {
    postRepository.delete(postId);
    }


}
