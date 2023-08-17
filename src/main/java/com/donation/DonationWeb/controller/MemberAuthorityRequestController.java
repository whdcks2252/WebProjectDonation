package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.MemberAuthorityRequest.dto.MemberAuthorityRequestDto;
import com.donation.DonationWeb.MemberAuthorityRequest.dto.MemberAuthorityRequestResponse;
import com.donation.DonationWeb.MemberAuthorityRequest.dto.MemberAuthorityResponseDto;
import com.donation.DonationWeb.MemberAuthorityRequest.service.MemberAuthorityRequestService;
import com.donation.DonationWeb.argumentresolver.Login;
import com.donation.DonationWeb.domain.MemberAuthorityRequest;
import com.donation.DonationWeb.post.dto.Result;
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
@RequestMapping("/memberAuthorityRequest")
@Slf4j
public class MemberAuthorityRequestController {
    private final MemberAuthorityRequestService memberAuthorityRequestService;

    @PostMapping
    public Object saveMemberAuthorityRequest(@RequestBody @Validated MemberAuthorityRequestDto memberAuthorityRequestDto, @Login Long loginId){
        MemberAuthorityRequest memberAuthorityRequest = memberAuthorityRequestService.saveMemberAuthorityRequest(memberAuthorityRequestDto, loginId);
        return new ResponseEntity<>(MemberAuthorityResponseDto.createInstance(memberAuthorityRequest),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Object findMemberAuthorityRequest(@PathVariable Long id) {
        MemberAuthorityRequest findMemberAuthorityRequest = memberAuthorityRequestService.findById(id);
        return new ResponseEntity<>(MemberAuthorityRequestResponse.createInstance(findMemberAuthorityRequest), HttpStatus.OK);
    }
    @GetMapping
    public Object findListPage(@RequestParam(defaultValue="1") Integer page) {
        List<MemberAuthorityRequest> findMemberAuthorityRequests = memberAuthorityRequestService.findByPage(page);

        List<MemberAuthorityRequestResponse> collect = findMemberAuthorityRequests.stream().map((m) -> MemberAuthorityRequestResponse.createInstance(m)).collect(Collectors.toList());
        return Result.createInstance(collect);
    }

}
