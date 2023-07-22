package com.donation.DonationWeb.controller;


import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.exception.LoginException;
import com.donation.DonationWeb.login.dto.LoginResponse;
import com.donation.DonationWeb.login.service.LoginService;
import com.donation.DonationWeb.member.dto.*;
import com.donation.DonationWeb.login.dto.LoginMemberRequest;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.post.dto.PostListResponse;
import com.donation.DonationWeb.post.dto.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.donation.DonationWeb.status.statusCode.RESPONSE_OK;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final MemberService memberService;
    private final LoginService loginService;

    @PostMapping("/join")
    public Object joinUser(@RequestBody @Validated AddMemberRequest addMemberRequest) {

        return new ResponseEntity<>(AddMemberResponse.
                createInstance(memberService.save(addMemberRequest)),HttpStatus.CREATED);
    }

    @GetMapping ("/{id}")
    public Object findByIdUser(@PathVariable @NotBlank Long id) {

        return AddMemberResponse.
                createInstance(memberService.findById(id));
    }

    @GetMapping("/{id}/post") //조회는 시간순으로 페이징 조회 10개씩
    public Object findUserPost(@PathVariable Long id,@RequestParam(defaultValue="1") Integer page) {
        Member findPosts = memberService.findUserPosts(id, page);

        return MemberPostResponse.createInstance(findPosts);
    }

    @GetMapping("/{id}/post/interest") //조회는 시간순으로 페이징 조회 10개씩
    public Object findUserInterestPosts(@PathVariable Long id,@RequestParam(defaultValue="1") Integer page) {
        Member findPosts = memberService.findUserInterestPosts(id, page);

        return MemberInterestPostResponse.createInstance(findPosts);
    }

    @PostMapping("/login")
    public Object login(@RequestBody @Validated LoginMemberRequest loginMemberRequest ) throws Exception {


        Optional<Member> memberIDAndPassword = memberService.findMemberIDAndPassword(loginMemberRequest);

        if(memberIDAndPassword.isPresent()) {
            log.info("{}",memberIDAndPassword.get().getId());
            loginService.loginUser(memberIDAndPassword.get().getId());

            return ResponseEntity.status(HttpStatus.OK).body(LoginResponse.createInstance(memberIDAndPassword.orElseThrow(() -> new LoginException()),RESPONSE_OK));
        }

        else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginMemberRequest);
        }


    }

    @GetMapping ("/logout")
    public Object logout( ) {
        loginService.logoutUser();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");

    }

    @PostMapping("/idCheck")
    public Object idCheck(@RequestBody @Validated IdCheckRequest memberId) {
        return IdCheckResponse.createInstance(memberId,memberService.idCheck(memberId));


    }
    @PostMapping("/nickNameCheck")
    public Object nickNameCheck(@RequestBody @Validated NicknameCheckRequest nickName) {
        log.info("nikc=",nickName);
        return NicknameCheckResponse.createInstance(nickName,memberService.nickNameCheck(nickName));


    }


}
