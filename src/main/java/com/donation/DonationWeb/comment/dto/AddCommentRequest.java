package com.donation.DonationWeb.comment.dto;

import com.donation.DonationWeb.domain.Comment;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class AddCommentRequest {
    @NotBlank
    private String content;

    public AddCommentRequest(String content) {
        this.content = content;
    }

    public Comment toEntity(Post post, Member member) {
        Comment createComment = Comment.builder()
                .content(content)
                .post(post)
                .member(member)
                .build();
        return createComment;
    }
}
