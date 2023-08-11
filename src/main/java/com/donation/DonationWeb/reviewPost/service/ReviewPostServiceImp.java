package com.donation.DonationWeb.reviewPost.service;

import com.donation.DonationWeb.category.service.CategoryService;
import com.donation.DonationWeb.domain.*;
import com.donation.DonationWeb.exception.PostException;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.post.service.PostService;
import com.donation.DonationWeb.reviewPost.dto.CreateReviewPostRequest;
import com.donation.DonationWeb.reviewPost.dto.UpdateReviewPostRequest;
import com.donation.DonationWeb.reviewPost.repository.ReviewPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ReviewPostServiceImp implements ReviewPostService{

    private final MemberService memberService;
    private final CategoryService categoryService;
    private final PostService postService;
    private final ReviewPostRepository reviewPostRepository;

    @Transactional
    @Override
    public ReviewPost savePost(CreateReviewPostRequest request, Long id) {
        return null;
//        Member member = memberService.findById(id);
//        Category category = categoryService.findByName(request.getCategoryName());
//        Post post = postService.findByTitle(request.getPostTitle());
//        return reviewPostRepository.save(request.toEntity(category,member,post));
    }

    @Override
    public ReviewPost findById(Long reviewPostId) {
        return reviewPostRepository.findById(reviewPostId).orElseThrow(() -> new PostException("not found reviewPostId: " + reviewPostId));
    }

    @Override
    public ReviewPost findByIdLeftJoin(Long reviewPostId) {
        return reviewPostRepository.findByIdLeftJoin(reviewPostId).orElseThrow(() -> new PostException("not found reviewPostId: " + reviewPostId));
    }

    @Override
    public List<ReviewPost> findByPage(Integer page) {
        return reviewPostRepository.findByPage(page);
    }

    @Override
    public List<ReviewPost> findByCategory(String categoryName, Integer page) {
        return reviewPostRepository.findByCategory(categoryService.findByName(categoryName).getId(), page);
    }

    @Override
    public ReviewPost findByPostTitle(String postTitle) {
        return null;
//        return reviewPostRepository.findByPostTitle(postService.findByTitle(postTitle).getId()).orElseThrow(() -> new PostException("not found postTitle: " + postTitle));
    }

    @Transactional
    @Override
    public void updatePost(UpdateReviewPostRequest request, Long reviewPostId, Long loginId) {


//        ReviewPost findReviewPost = findById(reviewPostId);
//
//        if (postMemberValidation(findReviewPost, loginId)) {
//            Category findCategory = categoryService.findByName(request.getCategoryName());
//            Post findpost = postService.findByTitle(request.getPostTitle());
//            reviewPostRepository.update(request,findReviewPost,findCategory,findpost);
//        }
//        else {
//            throw new PostException("업데이트가 실패 하였습니다");
//        }
    }

    @Transactional
    @Override
    public void delete(Long reviewPostId, Long loginId) {
        ReviewPost findReviewPost = findById(reviewPostId);

        if(postMemberValidation(findReviewPost,loginId) ){//멤버 아이디랑 게시물을 검증한다
            reviewPostRepository.delete(findReviewPost);

        }else
        {
            throw new PostException("게시물 삭제가 실패 하였습니다");
        }
    }

    private boolean postMemberValidation(ReviewPost reviewPost,Long loginId) {//로그인된 멤버 아이디랑 게시물을 검증한다
        Member findByLoginId = memberService.findById(loginId);

        if(findByLoginId.getId()==reviewPost.getMember().getId() ){
            return true;
        }

        return false;
    }
}
