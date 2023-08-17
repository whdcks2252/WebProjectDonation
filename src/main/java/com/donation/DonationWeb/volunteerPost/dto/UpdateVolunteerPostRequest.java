package com.donation.DonationWeb.volunteerPost.dto;

import com.donation.DonationWeb.domain.status.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UpdateVolunteerPostRequest {
    private String title;
    private String content;
    private Long categoryNum;
    private PostStatus postStatus;
    private Integer needAmount;
}
