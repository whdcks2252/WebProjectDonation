package com.donation.DonationWeb.post.dto;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.status.PostStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AddPostRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String categoryName;
    private PostStatus postStatus=PostStatus.PROCESS;// 생성시 진행중
    @NotNull
    private Integer targetAmount;
    private Integer currentAmount = 0;

    public AddPostRequest(String title, String content,String categoryName) {
        this.title = title;
        this.content = content;
        this.categoryName = categoryName;
    }

    public Post toEntity(Category categorie, Member member) {
        return Post.builder()
                .title(this.title).content(this.content)
                .postStatus(this.postStatus).categorie(categorie)
                .member(member).targetAmount(targetAmount).currentAmount(currentAmount)
                .build();
    }

}