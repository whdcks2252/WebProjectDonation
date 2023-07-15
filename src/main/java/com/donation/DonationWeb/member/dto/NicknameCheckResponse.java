package com.donation.DonationWeb.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NicknameCheckResponse {

    private String nickName;
    private String message;


    public static NicknameCheckResponse createInstance(NicknameCheckRequest nickName, String massage) {
        return new NicknameCheckResponse(nickName.getNickName(),massage

        );
    }
}
