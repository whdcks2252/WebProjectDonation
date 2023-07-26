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
public class UserPay extends ObjectTime{
    @Id
    @GeneratedValue
    @Column(name = "user_pay_num")
    private Long id;

    private Integer money;

    @OneToOne(mappedBy = "userPay")
    private Member member;
}
