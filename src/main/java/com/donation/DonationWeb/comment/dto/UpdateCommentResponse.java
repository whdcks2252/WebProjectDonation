package com.donation.DonationWeb.comment.dto;

import com.donation.DonationWeb.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UpdateCommentResponse {
    private Long commentId;
    private String content;
    private LocalDateTime updateTime;


    public static UpdateCommentResponse createInstance(Comment comment) {
        return new UpdateCommentResponse(comment.getId(),comment.getContent(),comment.getUpdateTime());
    }
}
