package com.donation.DonationWeb.kakaoPay.paymentApprove.service;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.PaymentApprove;
import com.donation.DonationWeb.domain.PaymentReady;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.exception.PaymentReadyException;
import com.donation.DonationWeb.kakaoPay.dto.DonationRequestDto;
import com.donation.DonationWeb.kakaoPay.dto.KakaoApproveResponse;
import com.donation.DonationWeb.kakaoPay.dto.KakaoReadyResponse;
import com.donation.DonationWeb.kakaoPay.paymentApprove.repository.PaymentApproveRepository;
import com.donation.DonationWeb.kakaoPay.paymentReady.repository.PaymentReadyRepository;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.post.service.PostService;
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


}
