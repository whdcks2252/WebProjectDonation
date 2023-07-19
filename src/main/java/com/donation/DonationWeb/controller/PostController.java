package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.argumentresolver.Login;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.post.dto.*;
import com.donation.DonationWeb.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@Slf4j
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public Object createPost(@RequestBody @Validated AddPostRequest addPostRequest, @Login Long id) {
        log.info("id={}",id);
        return new ResponseEntity<>(AddResponseRequest.createInstance(postService.savePost(addPostRequest,id)),HttpStatus.CREATED);

    }

    @GetMapping("/{postId}") //로그인 사용자만 접근가능
    public Object findById(@PathVariable Long postId) {
      return PostResponse.createInstance(postService.findById(postId));
    }

    @GetMapping("/list")//조회는 시간순으로 페이징 조회
    public Object findByIdPage(@RequestParam(defaultValue="0") Integer page) {
        List<Post> findPosts = postService.findByPage(page);
        List<PostResponse> collect = findPosts.stream().map((m) -> PostResponse.createInstance(m)).collect(Collectors.toList());
        return new Result(collect);
    }

    @GetMapping("/list/{categoryName}") //조회는 시간순으로 카테고리 이름으로 페이징 조회 10개씩
    public Object findByCategry(@PathVariable(name = "categoryName") String name,@RequestParam(defaultValue="0") Integer page) {
        List<Post> findPosts = postService.findByCategry(name, page);
        List<PostResponse> collect = findPosts.stream().map((m) -> PostResponse.createInstance(m)).collect(Collectors.toList());
        return new Result(collect);

    }

    @PatchMapping
    public Object postUpdate(@RequestBody @Validated UpdatePostRequest updatePostRequest, @Login  Long loginId) {
            postService.updatePost(updatePostRequest,loginId);

        return PostResponse.createInstance(postService.findById(updatePostRequest.getPostId()));
    }

    @DeleteMapping
    public Object postDelete(@RequestBody @Validated DeletePostRequest deletePostRequest, @Login Long id) {
            postService.delete(deletePostRequest,id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }





}
