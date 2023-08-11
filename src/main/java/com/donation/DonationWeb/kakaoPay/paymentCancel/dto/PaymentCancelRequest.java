package com.donation.DonationWeb.kakaoPay.paymentCancel.dto;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
public class PaymentCancelRequest {
    @NotEmpty
    private Long postId;
    @NotNull
    private String donationUUID;
}
