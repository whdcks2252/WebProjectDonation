package com.donation.DonationWeb.post.dto;

import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.status.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostListResponse {
    private Long PostId;
    private String title;
    private String categoryName;
    private Long categoryNum;
    private PostStatus postStatus = PostStatus.PROCESS;// 생성시 진행중
    private String memberId;
    private String memberNickname;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


    public static PostListResponse createInstance(Post post) {
        return new PostListResponse(post.getId(), post.getTitle(), post.getCategorie().getCategoryName(), post.getCategorie().getId(), post.getPostStatus(),
                post.getMember().getMemberId(), post.getMember().getMemberNickname(), post.getCreateTime(), post.getUpdateTime()


        );
    }


}

