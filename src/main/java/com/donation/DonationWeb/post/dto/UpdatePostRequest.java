package com.donation.DonationWeb.post.dto;

import com.donation.DonationWeb.domain.status.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UpdatePostRequest {
    private String title;
    private String content;
    private Long categoryNum;
    private PostStatus postStatus;// 생성시 진행중


}
