package com.donation.DonationWeb.volunteerPost.dto;

import com.donation.DonationWeb.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateVolunteerPostRequest {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String categoryName;
    @NotBlank
    private PostStatus postStatus;
    @NotNull
    private Integer targetAmount;
}
