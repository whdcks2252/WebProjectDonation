package com.donation.DonationWeb.post.dto;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddResponseRequest {
    private Long PostId;
    private String title;
    private String content;
    private String categoryName;
    private Long categoryNum;
    private PostStatus postStatus=PostStatus.PROCESS;// 생성시 진행중
    private String memberId;
    private String memberNickname;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;



    public static AddResponseRequest createInstance(Post post) {
        return new AddResponseRequest(post.getId(), post.getTitle(), post.getContent(), post.getCategorie().getCategoryName(),post.getCategorie().getId(), post.getPostStatus(),
                post.getMember().getMemberId(), post.getMember().getMemberNickname(), post.getCreateTime(), post.getUpdateTime());
    }


}
