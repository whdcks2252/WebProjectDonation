package com.donation.DonationWeb.volunteerComment.dto;

import com.donation.DonationWeb.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateVolunteerCommentRequest {
    @NotBlank
    private String content;

    public CreateVolunteerCommentRequest(String content) {
        this.content = content;
    }

    public VolunteerComment toEntity(VolunteerPost volunteerPost, Member member) {
        VolunteerComment createComment = VolunteerComment.builder()
                .content(content)
                .volunteerPost(volunteerPost)
                .member(member)
                .build();
        return createComment;
    }
}