package com.donation.DonationWeb.service;

import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void savePost(Post post){postRepository.save(post);}

    public List<Post> findPosts() {return postRepository.findAll();}

    public Post findOne(Long postId) {return postRepository.findOne(postId);}

    /**
     * 영속성 컨텍스트가 자동 변경
     */
    @Transactional
    public void updatePost(Long id,String content) {
        Post post = postRepository.findOne(id);
    }

}
