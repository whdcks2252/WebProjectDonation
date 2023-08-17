package com.donation.DonationWeb.reviewComment.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UpdateReviewCommentRequest {

    @NotBlank
    private String content;

}