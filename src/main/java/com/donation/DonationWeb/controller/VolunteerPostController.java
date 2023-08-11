package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.argumentresolver.Login;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.VolunteerPost;
import com.donation.DonationWeb.post.dto.PostListResponse;
import com.donation.DonationWeb.post.dto.PostResponse;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import com.donation.DonationWeb.volunteerPost.dto.*;
import com.donation.DonationWeb.volunteerPost.service.VolunteerPostService;
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
@RequestMapping("/api/volunteerPost")
@Slf4j
public class VolunteerPostController {

    private final VolunteerPostService volunteerPostService;

    @PostMapping
    public Object createVolunteerPost(@RequestBody @Validated CreateVolunteerPostRequest request, @Login Long id){
        log.info("id={}",id);
        return new ResponseEntity<>(CreateVolunteerPostResponse.createInstance(volunteerPostService.savePost(request,id)), HttpStatus.CREATED);
    }

    @GetMapping("/{volunteerPostId}")
    public Object findByIdLeftJoin(@PathVariable Long volunteerPostId) {
        VolunteerPost volunteerPost = volunteerPostService.findByIdLeftJoin(volunteerPostId);
        return VolunteerPostResponse.createInstance(volunteerPost);    // Lazy n+1문제 때문에 findByIdLeftJoin 호출
    }

    @GetMapping//조회는 시간순으로 페이징 조회
    public Object findByIdPage(@RequestParam(defaultValue="1") Integer page) {
        List<VolunteerPost> findPosts = volunteerPostService.findByPage(page);
        List<VolunteerPostListResponse> collect = findPosts.stream().map((m) -> VolunteerPostListResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);
    }

    @GetMapping("/list/{categoryName}") //조회는 시간순으로 카테고리 이름으로 페이징 조회 10개씩
    public Object findByCategory(@PathVariable(name = "categoryName") String name,@RequestParam(defaultValue="1") Integer page) {
        List<VolunteerPost> findPosts = volunteerPostService.findByCategory(name, page);
        List<VolunteerPostListResponse> collect = findPosts.stream().map((m) -> VolunteerPostListResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);
    }

    @PatchMapping("/{volunteerPostId}")
    public Object updateVolunteerPost(@PathVariable Long volunteerPostId, @RequestBody @Validated UpdateVolunteerPostRequest request, @Login  Long loginId) {
        volunteerPostService.updatePost(request,volunteerPostId,loginId);
        // Lazy n+1문제 때문에 findByIdLeftJoin 호출
        return VolunteerPostResponse.createInstance(volunteerPostService.findByIdLeftJoin(volunteerPostId));
    }

    @DeleteMapping("/{volunteerPostId}")
    public Object postDelete(@PathVariable Long volunteerPostId, @Login Long loginId) {
        volunteerPostService.delete(volunteerPostId,loginId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
