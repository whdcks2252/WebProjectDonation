package com.donation.DonationWeb.reviewComment.dto;

import com.donation.DonationWeb.domain.Comment;
import com.donation.DonationWeb.domain.ReviewComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReviewCommentResponse {
    private Long commentId;
    private String content;
    private Long memberNum;
    private String memberId;
    private Long postId;
    private LocalDateTime updateTime;


    public static ReviewCommentResponse createInstance(ReviewComment reviewComment) {
        return new ReviewCommentResponse(reviewComment.getId(),reviewComment.getContent(),reviewComment.getMember().getId()
                ,reviewComment.getMember().getMemberId(),reviewComment.getReviewPost().getId(),reviewComment.getUpdateTime());
    }
}

