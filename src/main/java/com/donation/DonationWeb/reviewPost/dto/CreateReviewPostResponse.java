package com.donation.DonationWeb.reviewPost.dto;

import com.donation.DonationWeb.domain.PostStatus;
import com.donation.DonationWeb.domain.ReviewPost;
import com.donation.DonationWeb.domain.VolunteerPost;
import com.donation.DonationWeb.volunteerPost.dto.CreateVolunteerPostResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewPostResponse {
    private Long VolunteerPostId;
    private String title;
    private String content;
    private String categoryName;
    private Long categoryNum;
    private String postTitle;
    private Long postId;
    private String memberId;
    private String memberNickname;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static CreateReviewPostResponse createInstance(ReviewPost reviewPost) {
        return new CreateReviewPostResponse(reviewPost.getId(), reviewPost.getTitle(), reviewPost.getContent(), reviewPost.getCategorie().getCategoryName(),reviewPost.getCategorie().getId(), reviewPost.getPost().getTitle(), reviewPost.getPost().getId(),
                reviewPost.getMember().getMemberId(), reviewPost.getMember().getMemberNickname(), reviewPost.getCreateTime(), reviewPost.getUpdateTime());
    }
}
