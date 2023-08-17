package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.argumentresolver.Login;
import com.donation.DonationWeb.domain.VolunteerComment;
import com.donation.DonationWeb.volunteerComment.dto.CreateVolunteerCommentRequest;
import com.donation.DonationWeb.volunteerComment.dto.UpdateVolunteerCommentRequest;
import com.donation.DonationWeb.volunteerComment.dto.UpdateVolunteerCommentResponse;
import com.donation.DonationWeb.volunteerComment.dto.VolunteerCommentResponse;
import com.donation.DonationWeb.volunteerComment.service.VolunteerCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/volunteerPost/{volunteerPostId}/comment")
@Slf4j
public class VolunteerCommentController {

    private final VolunteerCommentService volunteerCommentService;
    @PostMapping
    public Object createComment(@PathVariable Long volunteerPostId, @RequestBody @Validated CreateVolunteerCommentRequest createVolunteerCommentRequest, @Login Long loginId) {

        VolunteerComment volunteerComment = volunteerCommentService.save(createVolunteerCommentRequest, loginId,volunteerPostId);
        return new ResponseEntity<>(VolunteerCommentResponse.createInstance(volunteerComment), HttpStatus.CREATED);

    }

    @PatchMapping("/{commentId}")
    public Object updateComment(@PathVariable Long commentId, @RequestBody UpdateVolunteerCommentRequest updateVolunteerCommentRequest, @Login Long loginId) {

        volunteerCommentService.update(updateVolunteerCommentRequest,commentId,loginId);
        return UpdateVolunteerCommentResponse.createInstance(volunteerCommentService.findById(commentId));

    }
    @DeleteMapping({"/{commentId}"})
    public Object deleteComment(@PathVariable Long commentId, @Login Long loginId) {

        volunteerCommentService.delete(commentId,loginId);

        return   new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

}
