package com.donation.DonationWeb.reviewComment.dto;

import com.donation.DonationWeb.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateReviewCommentRequest {
    @NotBlank
    private String content;

    public CreateReviewCommentRequest(String content) {
        this.content = content;
    }

    public ReviewComment toEntity(ReviewPost reviewPost, Member member) {
        ReviewComment createComment = ReviewComment.builder()
                .content(content)
                .reviewPost(reviewPost)
                .member(member)
                .build();
        return createComment;
    }
}
