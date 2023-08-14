package com.donation.DonationWeb.volunteerComment.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class UpdateVolunteerCommentRequest {

    @NotBlank
    private String content;

}
