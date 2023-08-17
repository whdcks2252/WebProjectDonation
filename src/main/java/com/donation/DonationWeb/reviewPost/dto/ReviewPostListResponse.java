package com.donation.DonationWeb.reviewPost.dto;

import com.donation.DonationWeb.domain.ReviewPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPostListResponse {
    private Long VolunteerPostId;
    private String title;
    private String categoryName;
    private Long categoryNum;
    private String postTitle;
    private Long postId;
    private String memberId;
    private String memberNickname;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static ReviewPostListResponse createInstance(ReviewPost reviewPost) {
        return new ReviewPostListResponse(reviewPost.getId(), reviewPost.getTitle(), reviewPost.getCategorie().getCategoryName(),reviewPost.getCategorie().getId(), reviewPost.getPost().getTitle(), reviewPost.getPost().getId(),
                reviewPost.getMember().getMemberId(), reviewPost.getMember().getMemberNickname(), reviewPost.getCreateTime(), reviewPost.getUpdateTime());
    }
}
