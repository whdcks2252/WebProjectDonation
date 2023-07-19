package com.donation.DonationWeb.post.dto;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.PostStatus;
import com.donation.DonationWeb.domain.ServiceAgreement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostResponse {
    private String title;
    private String content;
    private String categoryName;
    private PostStatus postStatus=PostStatus.PROCESS;// 생성시 진행중
    private String memberId;
    private String memberNickname;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;



    public static PostResponse createInstance(Post post) {
        return new PostResponse(post.getTitle(), post.getContent(), post.getCategorie().getCategoryName(), post.getPostStatus(),
                post.getMember().getMemberId(), post.getMember().getMemberNickname(), post.getCreateTime(), post.getUpdateTime());
    }


}
