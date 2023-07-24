package com.donation.DonationWeb.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Payment extends ObjectTime{
    @Id
    @GeneratedValue
    @Column(name = "payment_num")
    private Long id;

    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_num",nullable = false)
    private Post post;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_num",nullable = false)
    private Member member;

}
