package com.donation.DonationWeb.kakaoPay.paymentReady.service;
import com.donation.DonationWeb.domain.PaymentReady;
import com.donation.DonationWeb.kakaoPay.dto.DonationRequestDto;
import com.donation.DonationWeb.kakaoPay.dto.KakaoReadyResponse;

public interface PaymentReadyService {
    PaymentReady save(KakaoReadyResponse kakaoReadyResponse,DonationRequestDto donationRequestDto, Long postId, Long loginId);
    PaymentReady findWithMemberAndPostAndUUID(Long memberId,Long postId,String donationUUID);

}
