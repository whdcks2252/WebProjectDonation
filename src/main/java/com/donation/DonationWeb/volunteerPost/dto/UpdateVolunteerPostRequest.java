package com.donation.DonationWeb.volunteerPost.dto;

import com.donation.DonationWeb.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UpdateVolunteerPostRequest {
    private String title;
    private String content;
    private String categoryName;
    private PostStatus postStatus;
    private Integer needAmount;
}
