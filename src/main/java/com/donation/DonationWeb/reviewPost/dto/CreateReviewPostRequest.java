package com.donation.DonationWeb.reviewPost.dto;

import com.donation.DonationWeb.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateReviewPostRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String categoryName;
    @NotNull
    private Long postId;

    public CreateReviewPostRequest(String title, String content, String categoryName, Long postId) {
        this.title = title;
        this.content = content;
        this.categoryName = categoryName;
        this.postId = postId;
    }

    public ReviewPost toEntity(Category categorie, Post post) {
        return ReviewPost.builder()
                .title(this.title).content(this.content)
                .categorie(categorie)
                .post(post)
                .build();
    }
}
