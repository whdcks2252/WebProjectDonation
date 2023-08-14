package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.InterestPost.sevice.InterestPostService;
import com.donation.DonationWeb.InterestVolunteerPost.service.InterestVolunteerPostService;
import com.donation.DonationWeb.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/volunteerPost/{volunteerPostId}/interest")
@Slf4j
public class InterestVolunteerPostController {
    private final InterestVolunteerPostService interestVolunteerPostService;
    @PutMapping //put매핑 관심게시판 등록 요청이 들어오면 생성  자원이 있으면 삭제
    public Object interestPostPut(@PathVariable Long volunteerPostId, @Login Long loginId) {
        if( interestVolunteerPostService.interestPostPut(volunteerPostId,loginId).isPresent()){

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    }

    @DeleteMapping
    public Object interestPostDelete(@PathVariable Long volunteerPostId, @Login Long loginId) {


        interestVolunteerPostService.interestPostDelete(volunteerPostId,loginId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
