package com.donation.DonationWeb.volunteerPost.dto;

import com.donation.DonationWeb.domain.VolunteerPost;
import com.donation.DonationWeb.domain.status.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVolunteerPostResponse {
    private Long VolunteerPostId;
    private String title;
    private String content;
    private String categoryName;
    private Long categoryNum;
    private PostStatus postStatus=PostStatus.PROCESS;// 생성시 진행중
    private String memberId;
    private String memberNickname;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public static CreateVolunteerPostResponse createInstance(VolunteerPost volunteerPost) {
        return new CreateVolunteerPostResponse(volunteerPost.getId(), volunteerPost.getTitle(), volunteerPost.getContent(), volunteerPost.getCategorie().getCategoryName(),volunteerPost.getCategorie().getId(), volunteerPost.getPostStatus(),
                volunteerPost.getMember().getMemberId(), volunteerPost.getMember().getMemberNickname(), volunteerPost.getCreateTime(), volunteerPost.getUpdateTime());
    }
}
