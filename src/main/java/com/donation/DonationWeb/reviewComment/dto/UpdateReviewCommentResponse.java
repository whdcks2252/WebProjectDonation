package com.donation.DonationWeb.reviewComment.dto;

import com.donation.DonationWeb.domain.ReviewComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UpdateReviewCommentResponse {
    private Long commentId;
    private String content;
    private LocalDateTime updateTime;


    public static UpdateReviewCommentResponse createInstance(ReviewComment reviewComment) {
        return new UpdateReviewCommentResponse(reviewComment.getId(),reviewComment.getContent(),reviewComment.getUpdateTime());
    }
}
