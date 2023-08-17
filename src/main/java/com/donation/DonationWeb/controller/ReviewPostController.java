package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.argumentresolver.Login;
import com.donation.DonationWeb.domain.ReviewPost;
import com.donation.DonationWeb.reviewPost.dto.*;
import com.donation.DonationWeb.reviewPost.service.ReviewPostService;
import com.donation.DonationWeb.volunteerPost.dto.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviewPost")
@Slf4j
public class ReviewPostController {

    private final ReviewPostService reviewPostService;

    @PostMapping
    public Object createReviewPost(@RequestBody @Validated CreateReviewPostRequest request, @Login Long id){
        log.info("id={}",id);
        return new ResponseEntity<>(CreateReviewPostResponse.createInstance(reviewPostService.savePost(request,id)), HttpStatus.CREATED);
    }

    @GetMapping("/{reviewPostId}")
    public Object findByIdLeftJoin(@PathVariable Long reviewPostId) {
        ReviewPost reviewPost = reviewPostService.findByIdLeftJoin(reviewPostId);
        return ReviewPostResponse.createInstance(reviewPost);    // Lazy n+1문제 때문에 findByIdLeftJoin 호출
    }

    @GetMapping//조회는 시간순으로 페이징 조회
    public Object findByIdPage(@RequestParam(defaultValue="1") Integer page) {
        List<ReviewPost> findPosts = reviewPostService.findByPage(page);
        List<ReviewPostListResponse> collect = findPosts.stream().map((m) -> ReviewPostListResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);
    }

    @GetMapping("/list/{categoryName}") //조회는 시간순으로 카테고리 이름으로 페이징 조회 10개씩
    public Object findByCategory(@PathVariable(name = "categoryName") String name,@RequestParam(defaultValue="1") Integer page) {
        List<ReviewPost> findPosts = reviewPostService.findByCategory(name, page);
        List<ReviewPostListResponse> collect = findPosts.stream().map((m) -> ReviewPostListResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);
    }

    @PatchMapping("/{reviewPostId}")
    public Object updateReviewPost(@PathVariable Long reviewPostId, @RequestBody @Validated UpdateReviewPostRequest request, @Login  Long loginId) {
        reviewPostService.updatePost(request,reviewPostId,loginId);
        // Lazy n+1문제 때문에 findByIdLeftJoin 호출
        return ReviewPostResponse.createInstance(reviewPostService.findByIdLeftJoin(reviewPostId));
    }

    @DeleteMapping("/{reviewPostId}")
    public Object postDelete(@PathVariable Long reviewPostId, @Login Long loginId) {
        reviewPostService.delete(reviewPostId,loginId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
