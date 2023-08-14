package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.argumentresolver.Login;
import com.donation.DonationWeb.domain.ReviewComment;
import com.donation.DonationWeb.reviewComment.dto.CreateReviewCommentRequest;
import com.donation.DonationWeb.reviewComment.dto.ReviewCommentResponse;
import com.donation.DonationWeb.reviewComment.dto.UpdateReviewCommentRequest;
import com.donation.DonationWeb.reviewComment.dto.UpdateReviewCommentResponse;
import com.donation.DonationWeb.reviewComment.service.ReviewCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviewPost/{reviewPostId}/comment")
@Slf4j
public class ReviewCommentController {

    private final ReviewCommentService reviewCommentService;
    @PostMapping
    public Object createComment(@PathVariable Long reviewPostId, @RequestBody @Validated CreateReviewCommentRequest createReviewCommentRequest, @Login Long loginId) {
        log.info("{}",createReviewCommentRequest.getContent());
        ReviewComment reviewComment = reviewCommentService.save(createReviewCommentRequest, loginId,reviewPostId);
        return new ResponseEntity<>(ReviewCommentResponse.createInstance(reviewComment), HttpStatus.CREATED);

    }

    @PatchMapping("/{commentId}")
    public Object updateComment(@PathVariable Long commentId, @RequestBody UpdateReviewCommentRequest updateReviewCommentRequest, @Login Long loginId) {

        reviewCommentService.update(updateReviewCommentRequest,commentId,loginId);
        return UpdateReviewCommentResponse.createInstance(reviewCommentService.findById(commentId));

    }
    @DeleteMapping({"/{commentId}"})
    public Object deleteComment(@PathVariable Long commentId, @Login Long loginId) {

        reviewCommentService.delete(commentId,loginId);

        return   new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

}
