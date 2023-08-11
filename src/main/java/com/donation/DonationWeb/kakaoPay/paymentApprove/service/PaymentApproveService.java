package com.donation.DonationWeb.kakaoPay.paymentApprove.service;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.PaymentApprove;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.kakaoPay.dto.KakaoApproveResponse;

public interface PaymentApproveService {
    PaymentApprove save(KakaoApproveResponse kakaoReadyResponse, Post post, Member member);

}
