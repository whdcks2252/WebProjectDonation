package com.donation.DonationWeb.kakaoPay.paymentReady.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
public class DonationRequestDto {
    @NotBlank
    String donationUUID;
    @NotNull
    Integer donationPrice;
}