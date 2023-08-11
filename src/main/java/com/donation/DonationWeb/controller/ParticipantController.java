package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.argumentresolver.Login;
import com.donation.DonationWeb.participant.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/volunteerPost/{volunteerPostId}/participate")
@Slf4j
public class ParticipantController {

    private final ParticipantService participantService;

    @PutMapping //put매핑 관심게시판 등록 요청이 들어오면 생성  자원이 있으면 삭제
    public Object participantPut(@PathVariable Long volunteerPostId, @Login Long loginId) {
        if( participantService.participantPut(volunteerPostId,loginId).isPresent()){

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else{

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
    }
}
