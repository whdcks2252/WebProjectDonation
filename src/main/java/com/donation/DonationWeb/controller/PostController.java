package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.argumentresolver.Login;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.post.dto.*;
import com.donation.DonationWeb.post.service.PostService;
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
@RequestMapping("/api/post")
@Slf4j
public class PostController {
    private final PostService postService;

    @PostMapping
    public Object createPost(@RequestBody @Validated AddPostRequest addPostRequest, @Login Long loginId) {
        log.info("id={}",loginId);
        return new ResponseEntity<>(AddPostResponse.createInstance(postService.savePost(addPostRequest,loginId)),HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public Object findByIdLeftJoin(@PathVariable Long postId) {
      return PostResponse.createInstance(postService.findByIdLeftJoin(postId));    // Lazy n+1문제 때문에 findByIdLeftJoin 호출
    }

    @GetMapping//조회는 시간순으로 페이징 조회
    public Object findByIdPage(@RequestParam(defaultValue="1") Integer page) {
        List<Post> findPosts = postService.findByPage(page);
        List<PostListResponse> collect = findPosts.stream().map((m) -> PostListResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);
    }

    @GetMapping("/list/{categoryName}") //조회는 시간순으로 카테고리 이름으로 페이징 조회 10개씩
    public Object findByCategry(@PathVariable(name = "categoryName") String name,@RequestParam(defaultValue="1") Integer page) {
        List<Post> findPosts = postService.findByCategry(name, page);
        List<PostListResponse> collect = findPosts.stream().map((m) -> PostListResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);

    }

    @PatchMapping("/{id}")
    public Object postUpdate(@PathVariable(name = "id") Long postid,@RequestBody @Validated UpdatePostRequest updatePostRequest, @Login Long loginId) {
            postService.updatePost(updatePostRequest,postid,loginId);
        // Lazy n+1문제 때문에 findByIdLeftJoin 호출
        return PostResponse.createInstance(postService.findByIdLeftJoin(postid));
    }

    @DeleteMapping("/{id}")
    public Object postDelete(@PathVariable(name = "id") Long postId, @Login Long loginId) {
            postService.delete(postId,loginId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }





}
