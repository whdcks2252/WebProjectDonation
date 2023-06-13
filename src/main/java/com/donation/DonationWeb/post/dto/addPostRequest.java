package com.donation.DonationWeb.post.dto;

import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class addPostRequest {

    private String title;
    private String content;

    public Post toEntity() {
        return Post.builder().title(this.title).content(this.content).postStatus(PostStatus.PROCESS).build();
    }

}
