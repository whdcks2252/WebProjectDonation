package com.donation.DonationWeb.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IdCheckResponse {

    private String idCheck;
    private String message;


    public static IdCheckResponse createInstance(IdCheckRequest id, String massage) {
        return new IdCheckResponse(id.getId(),massage

        );
    }
}
