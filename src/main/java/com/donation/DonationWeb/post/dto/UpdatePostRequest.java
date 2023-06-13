package com.donation.DonationWeb.post.dto;

import com.donation.DonationWeb.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder// 테스트 용
public class UpdatePostRequest {
    private String title;
    private String content;
    private PostStatus postStatus;
}
