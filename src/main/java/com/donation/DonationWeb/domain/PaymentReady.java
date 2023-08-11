package com.donation.DonationWeb.domain;

import com.donation.DonationWeb.kakaoPay.paymentReady.dto.DonationRequestDto;
import com.donation.DonationWeb.kakaoPay.dto.KakaoReadyResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentReady {
    @Builder
    public PaymentReady(String donationUUID, String tid, LocalDateTime created_at, Integer price, Member member, Post post) {
        this.donationUUID = donationUUID;
        this.tid = tid;
        this.created_at = created_at;
        this.price = price;
        this.member = member;
        this.post = post;
    }

    @Id
    @GeneratedValue
    @Column(name = "paymentReady_num")
    private Long id;

    private String donationUUID;//기부 고유 번호
    private String tid;// 결제 고유 번호
    private LocalDateTime created_at;//결제 준비 요청 시간
    private Integer price;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_num",nullable = false)
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "post_num",nullable = false)
    private Post post;


    public static PaymentReady toEntity(KakaoReadyResponse kakaoReadyResponse, DonationRequestDto donationRequestDto, Member member, Post post) {
        return PaymentReady.builder()
                .donationUUID(donationRequestDto.getDonationUUID())
                .tid(kakaoReadyResponse.getTid())
                .created_at(kakaoReadyResponse.getCreated_at())
                .price(donationRequestDto.getDonationPrice())
                .member(member)
                .post(post)
                .build();
    }

}
