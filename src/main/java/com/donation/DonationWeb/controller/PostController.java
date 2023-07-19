package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.argumentresolver.Login;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.post.dto.AddPostRequest;
import com.donation.DonationWeb.post.dto.DeletePostRequest;
import com.donation.DonationWeb.post.dto.PostResponse;
import com.donation.DonationWeb.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@Slf4j
public class PostController {
    private final PostService postService;

    @PostMapping("/create")
    public void createPost(@RequestBody @Validated AddPostRequest addPostRequest,@Login Long id) {
        log.info("id={}",id);
        postService.savePost(addPostRequest,id);

    }
    @GetMapping("/{postId}") //로그인 사용자만 접근가능
    public Object findById(@PathVariable Long postId) {
      return PostResponse.createInstance(postService.findById(postId));
    }
    @GetMapping("/list")//조회는 시간순으로
    public Object findByIdPage(@RequestParam(defaultValue="0") Integer page) {

        return postService.findByPage(page);
    }
    @GetMapping("/list/{categoryName}") //조회는 시간순으로
    public Object findByCategry(@PathVariable(name = "categoryName") String name,@RequestParam(defaultValue="0") Integer page) {
        log.info("categoryName={}",name);

        return postService.findByCategry(name, page);

    }

    @DeleteMapping
    public void postDelete(@RequestBody DeletePostRequest deletePostRequest, @Login Long id) {
            postService.delete(deletePostRequest,id);

    }





}
