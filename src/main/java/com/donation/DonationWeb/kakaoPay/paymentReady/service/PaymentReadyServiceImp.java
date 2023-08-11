package com.donation.DonationWeb.kakaoPay.paymentReady.service;

import com.donation.DonationWeb.domain.*;
import com.donation.DonationWeb.exception.PaymentReadyException;
import com.donation.DonationWeb.kakaoPay.dto.DonationRequestDto;
import com.donation.DonationWeb.kakaoPay.dto.KakaoReadyResponse;
import com.donation.DonationWeb.kakaoPay.paymentReady.repository.PaymentReadyRepository;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentReadyServiceImp implements PaymentReadyService {
    private final PaymentReadyRepository paymentReadyRepository;
    private final MemberService memberService;
    private final PostService postService;

    @Transactional
    @Override
    public PaymentReady save(KakaoReadyResponse kakaoReadyResponse,DonationRequestDto donationRequestDto, Long postId, Long loginId) {
        Post findPost = postService.findById(postId);
        Member findMember = memberService.findById(loginId);
       return paymentReadyRepository.save(PaymentReady.toEntity(kakaoReadyResponse, donationRequestDto, findMember, findPost));
    }

    @Override
    public PaymentReady findWithMemberAndPostAndUUID(Long memberId, Long postId, String donationUUID) {
        PaymentReady paymentReady = paymentReadyRepository.findWithMemberAndPostAndUUID(memberId, postId, donationUUID)
                .orElseThrow(() -> new PaymentReadyException("paymentReady를 찾을수없습니다 "));
        return paymentReady;

    }
}
