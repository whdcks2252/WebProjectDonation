package com.donation.DonationWeb.kakaoPay.service;

import com.donation.DonationWeb.domain.*;
import com.donation.DonationWeb.domain.status.PostStatus;
import com.donation.DonationWeb.exception.KakaoPayException;
import com.donation.DonationWeb.kakaoPay.paymentCancel.service.PaymentCancelService;
import com.donation.DonationWeb.kakaoPay.paymentReady.dto.DonationRequestDto;
import com.donation.DonationWeb.kakaoPay.dto.KakaoApproveResponse;
import com.donation.DonationWeb.kakaoPay.dto.KakaoCancelResponse;
import com.donation.DonationWeb.kakaoPay.dto.KakaoReadyResponse;
import com.donation.DonationWeb.kakaoPay.paymentApprove.service.PaymentApproveService;
import com.donation.DonationWeb.kakaoPay.paymentReady.service.PaymentReadyService;
import com.donation.DonationWeb.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class KakaoPayService {
    private final String cid = "TC0ONETIME";// 가맹점 테스트 코드
    @Value("${kakao.key}")
    private  String admin_Key;
    private final PaymentReadyService paymentReadyService;
    private final PaymentApproveService paymentApproveService;
    private final PaymentCancelService paymentCancelService;
    private final PostService postService;

    /**
     * 결제 요청
     */
    public KakaoReadyResponse kakaoPayReady(DonationRequestDto donationRequestDto, Long postId, Long loginId) {
        Post findPost = postService.findById(postId);
        postVaildate(findPost);

        //카카오페이 요청 양식
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("cid", cid);
        parameters.add("partner_order_id", donationRequestDto.getDonationUUID());
        parameters.add("partner_user_id", String.valueOf(loginId));
        parameters.add("item_name", String.valueOf(postId));
        parameters.add("quantity", "1");
        parameters.add("total_amount", String.valueOf(donationRequestDto.getDonationPrice()));
        parameters.add("tax_free_amount", "0");
        parameters.add("approval_url", "http://localhost:9090/kakaopay/success"+"?loginId="+loginId+"&postId="+postId+"&donationUUID="+donationRequestDto.getDonationUUID());
        parameters.add("cancel_url", "http://localhost:9090/kakaopay/cancel");
        parameters.add("fail_url", "http://localhost:9090/kakaopay/fail");


        //파라미터,헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoReadyResponse kakaoReadyResponse = restTemplate.postForObject(
           "https://kapi.kakao.com/v1/payment/ready",
             requestEntity, KakaoReadyResponse.class);

        paymentReadyService.save(kakaoReadyResponse, donationRequestDto, postId, loginId);

        return kakaoReadyResponse;
    }

    /**
     * 결제 완료 승인
     */
    public KakaoApproveResponse approveResponse(String pgToken,Long postId,Long loginId,String donationUUID) {
        PaymentReady findPaymentReady = paymentReadyService.findWithMemberAndPostAndUUID(loginId, postId, donationUUID);

        // 카카오 요청
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", findPaymentReady.getTid());
        parameters.add("partner_order_id",findPaymentReady.getDonationUUID());
        parameters.add("partner_user_id",String.valueOf(loginId));
        parameters.add("pg_token", pgToken);

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoApproveResponse approveResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/approve",
                requestEntity,
                KakaoApproveResponse.class);
        Post findPost = findPaymentReady.getPost();
        paymentApproveService.save(approveResponse, findPost, findPaymentReady.getMember());

        try {
            postService.updateCurrentAmount(postId, approveResponse.getAmount().getTotal());
        } catch (Exception e) {//결제중 오류시 카카오페이도 취소 요청
            paymentError(approveResponse);
            throw new KakaoPayException("결제중 오류");
        }

        return approveResponse;
    }

    /**
     * 결제 환불
     */
    public KakaoCancelResponse kakaoCancel(Long paymentId, Long loginId) {
        PaymentApprove findPaymentApprove = paymentApproveService.findById(paymentId);
        memberVaildate(findPaymentApprove.getMember(),loginId);// 로그인 회원 검증

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", findPaymentApprove.getTid());
        parameters.add("cancel_amount", String.valueOf(findPaymentApprove.getPrice()));
        parameters.add("cancel_tax_free_amount", String.valueOf(findPaymentApprove.getTax_free()));

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoCancelResponse cancelResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/cancel",
                requestEntity,
                KakaoCancelResponse.class);
        paymentCancelService.save(cancelResponse,findPaymentApprove);
        Post findPost = findPaymentApprove.getPost();
        postService.updateCurrentAmount(findPost.getId(),-(cancelResponse.getAmount().getTotal()));
        findPaymentApprove.paymentCancel();
        return cancelResponse;

    }

    /**
     * 카카오 요구 헤더값
     */
    private HttpHeaders getHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();

        String auth="KakaoAK " + admin_Key;
        httpHeaders.add("Authorization", "KakaoAK " + admin_Key);
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpHeaders.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        return httpHeaders;
    }
    private void memberVaildate(Member member,Long loginId){
        if(!member.getId().equals(loginId)){

            throw new KakaoPayException("잘못된 접근입니다. loginId" + loginId);
        }

    }

    private void postVaildate(Post post) {
        if(post.getPostStatus().equals(PostStatus.EXPIRATION)){
            throw new KakaoPayException("만료된 기부게시물 입니다 postId: " + post.getId());
        }
    }

    private void paymentError(KakaoApproveResponse approveResponse){
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("cid", cid);
        parameters.add("tid", approveResponse.getTid());
        parameters.add("cancel_amount", String.valueOf(approveResponse.getAmount().getTotal()));
        parameters.add("cancel_tax_free_amount", String.valueOf(approveResponse.getAmount().getTax_free()));

        // 파라미터, 헤더
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());

        // 외부에 보낼 url
        RestTemplate restTemplate = new RestTemplate();

        KakaoCancelResponse cancelResponse = restTemplate.postForObject(
                "https://kapi.kakao.com/v1/payment/cancel",
                requestEntity,
                KakaoCancelResponse.class);
    }
}
