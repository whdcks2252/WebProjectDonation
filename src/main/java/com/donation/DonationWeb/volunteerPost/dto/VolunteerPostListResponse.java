package com.donation.DonationWeb.volunteerPost.dto;

import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.PostStatus;
import com.donation.DonationWeb.domain.VolunteerPost;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class VolunteerPostListResponse {
    private Long VolunteerPostId;
    private String title;
    private String categoryName;
    private Long categoryNum;
    private PostStatus postStatus = PostStatus.PROCESS;// 생성시 진행중
    private String memberId;
    private String memberNickname;
    private Integer needAmount;
    private Integer currentParticipantAmount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static VolunteerPostListResponse createInstance(VolunteerPost volunteerPost) {
        return new VolunteerPostListResponse(volunteerPost.getId(), volunteerPost.getTitle(), volunteerPost.getCategorie().getCategoryName(), volunteerPost.getCategorie().getId(), volunteerPost.getPostStatus(),
                volunteerPost.getMember().getMemberId(), volunteerPost.getMember().getMemberNickname(), volunteerPost.getNeedAmount(), volunteerPost.getCurrentParticipantAmount(), volunteerPost.getCreateTime(), volunteerPost.getUpdateTime()
        );
    }
}
