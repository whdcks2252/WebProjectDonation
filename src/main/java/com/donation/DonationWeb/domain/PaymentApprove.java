package com.donation.DonationWeb.domain;

import com.donation.DonationWeb.domain.status.CancelStatus;
import com.donation.DonationWeb.kakaoPay.dto.KakaoApproveResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentApprove {

    @Builder
    public PaymentApprove(String donationUUID, String tid, String cid, LocalDateTime created_at, LocalDateTime approved_at, Integer price, Integer tax_free, Member member, Post post, CancelStatus cancelStatus) {
        this.donationUUID = donationUUID;
        this.tid = tid;
        this.cid = cid;
        this.created_at = created_at;
        this.approved_at = approved_at;
        this.price = price;
        this.tax_free = tax_free;
        this.member = member;
        this.post = post;
        this.cancelStatus = cancelStatus;
    }

    @Id
    @GeneratedValue
    @Column(name = "paymentApprove_num")
    private Long id;

    private String donationUUID;//기부 고유 번호
    private String tid;// 결제 고유 번호
    private String cid;//가맹점 코드
    private LocalDateTime created_at;//결제 준비 요청 시간
    private LocalDateTime approved_at; // 결제 승인 시간
    private Integer price;// 총액
    private Integer tax_free; // 비과세 금액

    @Enumerated(EnumType.STRING)
    private CancelStatus cancelStatus;//환불 상태

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_num",nullable = false,foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "post_num",nullable = false,foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Post post;


    public static PaymentApprove toEntity(KakaoApproveResponse kakaoApproveResponse, Post post,Member member) {
        return PaymentApprove.builder()
                .donationUUID(kakaoApproveResponse.getPartner_order_id())
                .tid(kakaoApproveResponse.getTid())
                .cid(kakaoApproveResponse.getCid())
                .created_at(kakaoApproveResponse.getCreated_at())
                .approved_at(kakaoApproveResponse.getApproved_at())
                .price(kakaoApproveResponse.getAmount().getTotal())
                .tax_free(kakaoApproveResponse.getAmount().getTax_free())
                .member(member)
                .post(post)
                .cancelStatus(CancelStatus.PROCESS)
                .build();
    }
    public  void paymentCancel(){
        this.cancelStatus = CancelStatus.CANCEL;
    }

}
