package com.donation.DonationWeb.volunteerComment.dto;

import com.donation.DonationWeb.domain.VolunteerComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UpdateVolunteerCommentResponse {
    private Long commentId;
    private String content;
    private LocalDateTime updateTime;


    public static UpdateVolunteerCommentResponse createInstance(VolunteerComment volunteerComment) {
        return new UpdateVolunteerCommentResponse(volunteerComment.getId(),volunteerComment.getContent(),volunteerComment.getUpdateTime());
    }
}
