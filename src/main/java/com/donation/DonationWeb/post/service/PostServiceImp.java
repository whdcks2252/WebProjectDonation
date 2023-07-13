package com.donation.DonationWeb.post.service;

import com.donation.DonationWeb.category.service.CategoryService;
import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.member.service.MemberService;
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
    private final MemberService memberService;
    private final CategoryService categoryService;

    @Transactional
    public Post savePost(AddPostRequest addPostRequest,Long id){
        Member member = memberService.findById(id);
        Category category = categoryService.findById(addPostRequest.getCategoryId());
        return postRepository.save(addPostRequest.toEntity(category,member));
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
