package com.donation.DonationWeb.comment.dto;

import com.donation.DonationWeb.domain.Comment;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AddCommentRequest {
    @NotBlank
    private String content;

    public Comment toEntity(Post post, Member member) {
        Comment createComment = Comment.builder()
                .content(content)
                .post(post)
                .member(member)
                .build();
        return createComment;
    }
}