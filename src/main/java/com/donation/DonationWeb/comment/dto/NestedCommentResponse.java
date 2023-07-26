package com.donation.DonationWeb.comment.dto;

import com.donation.DonationWeb.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class NestedCommentResponse {
    private Long commentId;
    private String content;
    private Long memberNum;
    private String memberId;
    private Long postId;
    private LocalDateTime updateTime;


    public static NestedCommentResponse createInstance(Comment comment) {
        return new NestedCommentResponse(comment.getId(),comment.getContent(),comment.getMember().getId()
                ,comment.getMember().getMemberId(),comment.getPost().getId(),comment.getUpdateTime());
    }
}
