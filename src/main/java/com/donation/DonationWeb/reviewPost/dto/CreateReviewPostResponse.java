package com.donation.DonationWeb.reviewPost.dto;

import com.donation.DonationWeb.domain.ReviewPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewPostResponse {
    private Long reviewPostId;
    private String title;
    private String content;
    private String categoryName;
    private Long categoryNum;
    private Long postId;
    private String memberId;
    private String memberNickname;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static CreateReviewPostResponse createInstance(ReviewPost reviewPost) {
        return new CreateReviewPostResponse(reviewPost.getId(), reviewPost.getTitle(), reviewPost.getContent(), reviewPost.getCategorie().getCategoryName(),reviewPost.getCategorie().getId(), reviewPost.getPost().getId(),
                reviewPost.getPost().getMember().getMemberId(), reviewPost.getPost().getMember().getMemberNickname(), reviewPost.getCreateTime(), reviewPost.getUpdateTime());
    }
}
