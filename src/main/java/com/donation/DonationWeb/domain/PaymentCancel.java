package com.donation.DonationWeb.domain;

import com.donation.DonationWeb.kakaoPay.dto.KakaoCancelResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentCancel {
    @Builder
    public PaymentCancel(String donationUUID, String tid, String cid, Integer price, LocalDateTime created_at, LocalDateTime approved_at, LocalDateTime canceled_at,PaymentApprove paymentApprove) {
        this.donationUUID = donationUUID;
        this.tid = tid;
        this.cid = cid;
        this.price = price;
        this.created_at = created_at;
        this.approved_at = approved_at;
        this.canceled_at = canceled_at;
        this.paymentApprove = paymentApprove;
    }

    @Id
    @GeneratedValue
    @Column(name = "PaymentCancel_num")
    private Long id;

    private String donationUUID;//기부 고유 번호
    private String tid;// 결제 고유 번호
    private String cid;//가맹점 코드
    private  Integer price;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "paymentApprove_num",nullable = false,foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PaymentApprove paymentApprove;

    private LocalDateTime created_at;
    private LocalDateTime approved_at;
    private LocalDateTime canceled_at;

    public static PaymentCancel toEntity(KakaoCancelResponse kakaoCancelResponse,PaymentApprove paymentApprove) {
        return PaymentCancel.builder()
                .donationUUID(kakaoCancelResponse.getPartner_order_id())
                .tid(kakaoCancelResponse.getTid())
                .cid(kakaoCancelResponse.getCid())
                .paymentApprove(paymentApprove)
                .price(kakaoCancelResponse.getAmount().getTotal())
                .created_at(kakaoCancelResponse.getCreated_at())
                .approved_at(kakaoCancelResponse.getApproved_at())
                .canceled_at(kakaoCancelResponse.getCanceled_at())
                .build();
    }

}
