package com.donation.DonationWeb.kakaoPay.dto;


import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class KakaoReadyResponse {
    private String tid;// 결제 고유 번호
    private String next_redirect_pc_url; // pc 웹일 경우 받는 결제 페이지 and 토큰 번호
    private LocalDateTime created_at;//결제 준비 요청 시간

}
