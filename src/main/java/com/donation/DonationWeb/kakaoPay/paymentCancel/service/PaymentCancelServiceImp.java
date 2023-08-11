package com.donation.DonationWeb.kakaoPay.paymentCancel.service;

import com.donation.DonationWeb.domain.*;
import com.donation.DonationWeb.kakaoPay.dto.KakaoCancelResponse;
import com.donation.DonationWeb.kakaoPay.paymentCancel.repository.PaymentCancelRepository;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentCancelServiceImp implements PaymentCancelService {
    private final PaymentCancelRepository paymentCancelRepository;
    private final MemberService memberService;
    private final PostService postService;

    @Transactional
    @Override
    public PaymentCancel save(KakaoCancelResponse kakaoCancelResponse, PaymentApprove paymentApprove) {
       return paymentCancelRepository.save(PaymentCancel.toEntity(kakaoCancelResponse,paymentApprove));
    }


}
