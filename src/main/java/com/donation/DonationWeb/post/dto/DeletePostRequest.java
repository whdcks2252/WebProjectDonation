package com.donation.DonationWeb.post.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DeletePostRequest {

    //@NotBlank는 String 타입에서 사용
    @NotNull
    private Long postId;

}
