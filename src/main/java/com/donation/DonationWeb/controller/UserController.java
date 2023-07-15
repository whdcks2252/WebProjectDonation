package com.donation.DonationWeb.controller;


import com.donation.DonationWeb.argumentresolver.Login;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.exception.UserException;
import com.donation.DonationWeb.login.dto.LoginResponse;
import com.donation.DonationWeb.login.service.LoginService;
import com.donation.DonationWeb.member.dto.*;
import com.donation.DonationWeb.login.dto.LoginMemberRequest;
import com.donation.DonationWeb.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

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


        Member save = memberService.save(addMemberRequest);

        return AddMemberResponse.createInstance(save);
    }

    @GetMapping ("/{id}")
    public Object findByIdUser(@PathVariable @NotBlank Long id) {


        log.info("{}", id);

        Member findById = memberService.findById(id);

        return AddMemberResponse.createInstance(findById);
    }

    @PostMapping("/login")
    public Object login(@RequestBody @Validated LoginMemberRequest loginMemberRequest,BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return bindingResult;
        }

        Optional<Member> memberIDAndPassword = memberService.findMemberIDAndPassword(loginMemberRequest);

        if(memberIDAndPassword.isPresent()) {
            log.info("{}",memberIDAndPassword.get().getId());
            loginService.loginUser(memberIDAndPassword.get().getId());

            return ResponseEntity.status(HttpStatus.OK).body(LoginResponse.createInstance(memberIDAndPassword.orElseThrow(() -> new UserException()),RESPONSE_OK));
        }

        else {
            bindingResult.reject("loginFail","아이디 또는 비밀번호가 맞지 않습니다");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(loginMemberRequest);
        }


    }

    @GetMapping ("/logout")
    public Object logout( ) {
        loginService.logoutUser();
        return ResponseEntity.status(HttpStatus.OK).body("");

    }

    @PostMapping("/idCheck")
    public Object idCheck(@RequestBody @Validated IdCheckRequest id) {
        return IdCheckResponse.createInstance(id,memberService.idCheck(id));


    }
    @PostMapping("/nickNameCheck")
    public Object nickNameCheck(@RequestBody @Validated NicknameCheckRequest nickName) {
        log.info("nikc=",nickName);
        return NicknameCheckResponse.createInstance(nickName,memberService.nickNameCheck(nickName));


    }


}
