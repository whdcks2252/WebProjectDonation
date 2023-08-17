package com.donation.DonationWeb.volunteerComment.dto;

import com.donation.DonationWeb.domain.VolunteerComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class VolunteerCommentResponse {
    private Long commentId;
    private String content;
    private Long memberNum;
    private String memberId;
    private Long postId;
    private LocalDateTime updateTime;


    public static VolunteerCommentResponse createInstance(VolunteerComment volunteerComment) {
        return new VolunteerCommentResponse(volunteerComment.getId(),volunteerComment.getContent(),volunteerComment.getMember().getId()
                ,volunteerComment.getMember().getMemberId(),volunteerComment.getVolunteerPost().getId(),volunteerComment.getUpdateTime());
    }
}
