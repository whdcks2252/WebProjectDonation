package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.argumentresolver.Login;
import com.donation.DonationWeb.exception.KakaoPayException;
import com.donation.DonationWeb.kakaoPay.paymentReady.dto.DonationRequestDto;
import com.donation.DonationWeb.kakaoPay.dto.KakaoApproveResponse;
import com.donation.DonationWeb.kakaoPay.dto.KakaoCancelResponse;
import com.donation.DonationWeb.kakaoPay.dto.KakaoReadyResponse;
import com.donation.DonationWeb.kakaoPay.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/kakaopay")
@Slf4j
public class KakaoPayController {
    private final KakaoPayService kakaoPayService;

    /**
     * 결제요청
     */
    @PostMapping("/ready/{postId}")
    public Object readyToKakaoPay(@PathVariable Long postId,@RequestBody @Validated DonationRequestDto donationRequestDto, @Login Long loginId) {
        KakaoReadyResponse kakaoReadyResponse = kakaoPayService.kakaoPayReady(donationRequestDto,postId,loginId);
        return kakaoReadyResponse;
    }
    /**
     * 결제 성공
     */
    @GetMapping ("/success")
    public Object afterPayRequest(@RequestParam("pg_token") String pgToken,@RequestParam Long postId,@RequestParam Long loginId,@RequestParam String donationUUID) {

        KakaoApproveResponse kakaoApprove = kakaoPayService.approveResponse(pgToken,postId,loginId,donationUUID);

        return new ResponseEntity<>(kakaoApprove, HttpStatus.OK);
    }

    /**
     * 결제 진행 중 취소
     */
    @GetMapping("/cancel")
    public void cancel() {

        throw new KakaoPayException("결제 진행 중 취소");
    }

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public void fail() {

        throw new KakaoPayException("결제 실패");
    }

    /**
     * 환불
     */
    @PostMapping("/refund/{paymentId}")
    public Object refund(@PathVariable Long paymentId, @Login Long loginId) {

        KakaoCancelResponse kakaoCancelResponse = kakaoPayService.kakaoCancel(paymentId,loginId);

        return new ResponseEntity<>(kakaoCancelResponse, HttpStatus.OK);
    }

}
