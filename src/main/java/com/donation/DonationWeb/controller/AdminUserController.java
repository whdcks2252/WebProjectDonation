package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.adminUser.dto.LoginAdminUserRequest;
import com.donation.DonationWeb.adminUser.dto.LoginAdminUserResponse;
import com.donation.DonationWeb.adminUser.service.AdminUserService;
import com.donation.DonationWeb.argumentresolver.Login;
import com.donation.DonationWeb.domain.*;
import com.donation.DonationWeb.exception.LoginException;
import com.donation.DonationWeb.login.dto.LoginMemberRequest;
import com.donation.DonationWeb.login.dto.LoginResponse;
import com.donation.DonationWeb.login.service.LoginService;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.participant.dto.ParticipantListResponse;
import com.donation.DonationWeb.participant.service.ParticipantService;
import com.donation.DonationWeb.post.dto.PostListResponse;
import com.donation.DonationWeb.post.dto.PostResponse;
import com.donation.DonationWeb.post.dto.Result;
import com.donation.DonationWeb.post.service.PostService;
import com.donation.DonationWeb.reviewPost.dto.ReviewPostListResponse;
import com.donation.DonationWeb.reviewPost.dto.ReviewPostResponse;
import com.donation.DonationWeb.reviewPost.service.ReviewPostService;
import com.donation.DonationWeb.volunteerPost.dto.VolunteerPostListResponse;
import com.donation.DonationWeb.volunteerPost.dto.VolunteerPostResponse;
import com.donation.DonationWeb.volunteerPost.service.VolunteerPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.donation.DonationWeb.status.statusCode.RESPONSE_OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Slf4j
public class AdminUserController {

    private final AdminUserService adminUserService;
    private final LoginService loginService;
    private final MemberService memberService;
    private final PostService postService;
    private final VolunteerPostService volunteerPostService;
    private final ParticipantService participantService;
    private final ReviewPostService reviewPostService;


    @PostMapping("/login")
    public Object login(@RequestBody @Validated LoginAdminUserRequest loginAdminUserRequest) throws Exception {


        Optional<AdminUser> adminUserIDAndPassword = adminUserService.findByAdminUserIdAndPassword(loginAdminUserRequest.getLoginId(), loginAdminUserRequest.getPassword());

        if (adminUserIDAndPassword.isPresent()) {
            log.info("{}", adminUserIDAndPassword.get().getId());
            loginService.loginUser(adminUserIDAndPassword.get().getId());

            return ResponseEntity.status(HttpStatus.OK).body(LoginAdminUserResponse.createInstance(adminUserIDAndPassword.orElseThrow(() -> new LoginException()), RESPONSE_OK));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginAdminUserRequest);
        }
    }

    @GetMapping ("/logout")
    public Object logout( ) {
        loginService.logoutUser();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    @GetMapping ("/posts")
    public Object findPostsByPage(@RequestParam(defaultValue="1") Integer page) {
        List<Post> findPosts = postService.findByPage(page);
        List<PostListResponse> collect = findPosts.stream().map((m) -> PostListResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);
    }

    @GetMapping("/posts/{postId}")
    public Object findPostByIdLeftJoin(@PathVariable Long postId) {
        return PostResponse.createInstance(postService.findByIdLeftJoin(postId));    // Lazy n+1문제 때문에 findByIdLeftJoin 호출
    }

    @GetMapping("/posts/list/{categoryName}") //조회는 시간순으로 카테고리 이름으로 페이징 조회 10개씩
    public Object findByCategry(@PathVariable(name = "categoryName") String name,@RequestParam(defaultValue="1") Integer page) {
        List<Post> findPosts = postService.findByCategry(name, page);
        List<PostListResponse> collect = findPosts.stream().map((m) -> PostListResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);

    }

    @DeleteMapping("/posts/{postId}")
    public Object postDelete(@PathVariable(name = "postId") Long postId) {
        postService.delete(postId,postService.findById(postId).getMember().getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping ("/volunteerPosts")
    public Object findVolunteerPostsByPage(@RequestParam(defaultValue="1") Integer page) {
        List<VolunteerPost> findPosts = volunteerPostService.findByPage(page);
        List<VolunteerPostListResponse> collect = findPosts.stream().map((m) -> VolunteerPostListResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);
    }

    @GetMapping("/volunteerPosts/{volunteerPostId}")
    public Object findVolunteerPostByIdLeftJoin(@PathVariable Long volunteerPostId) {
        return VolunteerPostResponse.createInstance(volunteerPostService.findByIdLeftJoin(volunteerPostId));    // Lazy n+1문제 때문에 findByIdLeftJoin 호출
    }

    @GetMapping("/volunteerPosts/list/{categoryName}") //조회는 시간순으로 카테고리 이름으로 페이징 조회 10개씩
    public Object findVolunteerPostsByCategory(@PathVariable(name = "categoryName") String name,@RequestParam(defaultValue="1") Integer page) {
        List<VolunteerPost> findPosts = volunteerPostService.findByCategory(name, page);
        List<VolunteerPostListResponse> collect = findPosts.stream().map((m) -> VolunteerPostListResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);
    }

    @DeleteMapping("/volunteerPosts/{volunteerPostId}")
    public Object volunteerPostDelete(@PathVariable(name = "volunteerPostId") Long volunteerPostId) {
        volunteerPostService.delete(volunteerPostId,volunteerPostService.findById(volunteerPostId).getMember().getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/volunteerPosts/{volunteerPostId}/participants") //조회는 시간순으로 봉사게시판의 참가자 페이징 조회 10개씩
    public Object findParticipantsByIdPage(@PathVariable(name = "volunteerPostId") Long volunteerPostId,@RequestParam(defaultValue="1") Integer page) {
        List<Participant> findParticipants = participantService.findByIdPage(volunteerPostId, page);
        List<ParticipantListResponse> collect = findParticipants.stream().map((m) -> ParticipantListResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);

    }

    @GetMapping ("/reviewPosts")
    public Object findReviewPostsByPage(@RequestParam(defaultValue="1") Integer page) {
        List<ReviewPost> findPosts = reviewPostService.findByPage(page);
        List<ReviewPostListResponse> collect = findPosts.stream().map((m) -> ReviewPostListResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);
    }

    @GetMapping("/reviewPosts/{reviewPostId}")
    public Object findReviewPostByIdLeftJoin(@PathVariable Long reviewPostId) {
        return ReviewPostResponse.createInstance(reviewPostService.findByIdLeftJoin(reviewPostId));    // Lazy n+1문제 때문에 findByIdLeftJoin 호출
    }

    @GetMapping("/reviewPosts/list/{categoryName}") //조회는 시간순으로 카테고리 이름으로 페이징 조회 10개씩
    public Object findReviewPostsByCategory(@PathVariable(name = "categoryName") String name,@RequestParam(defaultValue="1") Integer page) {
        List<ReviewPost> findPosts = reviewPostService.findByCategory(name, page);
        List<ReviewPostListResponse> collect = findPosts.stream().map((m) -> ReviewPostListResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);
    }

    @DeleteMapping("/reviewPosts/{reviewPostId}")
    public Object reviewPostDelete(@PathVariable(name = "reviewPostId") Long reviewPostId) {
        reviewPostService.delete(reviewPostId,reviewPostService.findById(reviewPostId).getPost().getMember().getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


}