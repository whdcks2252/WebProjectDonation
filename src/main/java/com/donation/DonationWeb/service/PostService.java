package com.donation.DonationWeb.service;

import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.dto.UpdatePostRequest;
import com.donation.DonationWeb.repository.PostRepository;
import com.donation.DonationWeb.repository.PostRepositoryImp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post savePost(Post post){ return postRepository.save(post);
    }

    public List<Post> findPosts() {return postRepository.findAll();}

    public Optional<Post> findById(Long postId) {return postRepository.findById(postId);}

    /**
     * 영속성 컨텍스트가 자동 변경
     */
    @Transactional
    public void updatePost(Long id, UpdatePostRequest upPost) { postRepository.update(id,upPost);}

}
