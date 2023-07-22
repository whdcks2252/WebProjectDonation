package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.InterestPost.sevice.InterestPostService;
import com.donation.DonationWeb.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post/{postId}/interest")
@Slf4j
public class InterestPostController {
    private final InterestPostService interestPostService;
    @PutMapping //put매핑 관심게시판 등록 요청이 들어오면 생성  자원이 있으면 삭제
    public Object interestPostPut(@PathVariable Long postId, @Login Long loginId) {
        if( interestPostService.interestPostPut(postId,loginId).isPresent()){

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    }

    @DeleteMapping
    public Object interestPostDelete(@PathVariable Long postId, @Login Long loginId) {


        interestPostService.interestPostDelete(postId,loginId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
