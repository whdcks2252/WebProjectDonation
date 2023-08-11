package com.donation.DonationWeb.kakaoPay.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CardInfo {
    private String purchase_corp;//매입 카드사 한글명
    private String purchase_corp_code; //매입 카드사 코드
    private String issuer_corp; //카드 발급사 한글명
    private String issuer_corp_code;//카드 발급사 코드
    private String kakaopay_purchase_corp;//카카오페이 매입사명
    private String kakaopay_purchase_corp_code;//카카오페이 매입사 카드
    private String kakaopay_issuer_corp;//카카오페이 발급사명
    private String kakaopay_issuer_corp_code;//카카오페이 발급사 코드

}
