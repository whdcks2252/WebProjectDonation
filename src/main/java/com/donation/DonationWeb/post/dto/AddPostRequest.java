package com.donation.DonationWeb.post.dto;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddPostRequest {

    private String title;
    private String content;
    private PostStatus postStatus=PostStatus.PROCESS;// 생성시 진행중

    public AddPostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity(Category categorie) {
        return Post.builder()
                .title(this.title).content(this.content)
                .postStatus(this.postStatus).categorie(categorie)
                .build();
    }

}
