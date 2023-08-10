package com.donation.DonationWeb.kakaoPay.paymentCancel.service;
import com.donation.DonationWeb.domain.*;
import com.donation.DonationWeb.kakaoPay.dto.KakaoCancelResponse;

public interface PaymentCancelService {
    public PaymentCancel save(KakaoCancelResponse kakaoCancelResponse, PaymentApprove paymentApprove);


}
