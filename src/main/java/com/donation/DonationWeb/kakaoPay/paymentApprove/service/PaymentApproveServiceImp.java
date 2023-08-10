package com.donation.DonationWeb.kakaoPay.paymentApprove.service;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.PaymentApprove;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.exception.KakaoPayException;
import com.donation.DonationWeb.kakaoPay.dto.KakaoApproveResponse;
import com.donation.DonationWeb.kakaoPay.paymentApprove.repository.PaymentApproveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentApproveServiceImp implements PaymentApproveService {
    private final PaymentApproveRepository paymentApproveRepository;

    @Transactional
    @Override
    public PaymentApprove save(KakaoApproveResponse kakaoApproveResponse,Post post,Member member) {
       return paymentApproveRepository.save(PaymentApprove.toEntity(kakaoApproveResponse,post,member));
    }

    @Override
    public PaymentApprove findById(Long paymentId) {
      return paymentApproveRepository.findById(paymentId).orElseThrow(()->new KakaoPayException("결제를 찾을 수 없습니다 "+paymentId));
    }



}
