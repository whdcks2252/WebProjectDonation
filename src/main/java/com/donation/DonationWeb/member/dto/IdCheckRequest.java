package com.donation.DonationWeb.member.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class IdCheckRequest {
    @NotBlank
    String memberId;
}