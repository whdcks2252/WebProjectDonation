package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.argumentresolver.Login;
import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.post.dto.AddPostRequest;
import com.donation.DonationWeb.post.service.PostService;
import com.donation.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
@Slf4j
public class PostController {
    private final PostService postService;


    @PostMapping
    public void createPost(@RequestBody @Validated AddPostRequest addPostRequest,@Login Long id) {
        log.info("id={}",id);
        postService.savePost(addPostRequest,id);

    }

    @GetMapping("/{id}")
    public void findByIdPost() {

    }

    @DeleteMapping
    public void postDelete() {

    }





}
