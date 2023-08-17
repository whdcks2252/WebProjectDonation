package com.donation.DonationWeb.reviewPost.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UpdateReviewPostRequest {
    private String title;
    private String content;
    private Long categoryNum;
    private Long postId;

}
