package com.donation.DonationWeb.controller;


import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.login.dto.LoginResponse;
import com.donation.DonationWeb.login.service.LoginService;
import com.donation.DonationWeb.member.dto.AddMemberRequest;
import com.donation.DonationWeb.member.dto.AddMemberResponse;
import com.donation.DonationWeb.login.dto.LoginMemberRequest;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.donation.DonationWeb.status.statusCode.RESPONSE_OK;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final MemberService memberService;
    private final LoginService loginService;

    @PostMapping
    public Object joinUser( @RequestBody @Validated AddMemberRequest addMemberRequest, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        
        Member save = memberService.save(addMemberRequest);

        return AddMemberResponse.createInstance(save);
    }

    @GetMapping ("/{id}")
    public Object findByIdUser(@PathVariable Long id) {

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
            loginService.loginUser(memberIDAndPassword.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(LoginResponse.createInstance(memberIDAndPassword.orElseThrow(() -> new Exception()),RESPONSE_OK));
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


    @GetMapping("/test")
    public Object test2(@SessionAttribute(name= SessionConst.LOGIN_MEMBER,required = false)Long id) {

        Member byId = memberService.findById(id);
        //  loginService.logoutUser();
        return byId;
    }

}
