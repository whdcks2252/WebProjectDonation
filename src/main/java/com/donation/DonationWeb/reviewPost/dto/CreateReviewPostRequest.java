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
    private String postTitle;

    public CreateReviewPostRequest(String title, String content, String categoryName, String postTitle) {
        this.title = title;
        this.content = content;
        this.categoryName = categoryName;
        this.postTitle = postTitle;
    }

    public ReviewPost toEntity(Category categorie, Member member, Post post) {
        return ReviewPost.builder()
                .title(this.title).content(this.content)
                .categorie(categorie).member(member)
                .post(post)
                .build();
    }
}
