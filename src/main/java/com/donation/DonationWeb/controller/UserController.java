package com.donation.DonationWeb.controller;


import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.member.dto.AddMemberRequest;
import com.donation.DonationWeb.member.dto.AddMemberResponse;
import com.donation.DonationWeb.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final MemberService memberService;

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

}
