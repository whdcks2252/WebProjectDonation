package com.donation.DonationWeb.comment.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class UpdateCommentRequest {

    @NotBlank
    private String content;

}
