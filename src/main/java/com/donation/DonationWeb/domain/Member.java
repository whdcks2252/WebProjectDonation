package com.donation.DonationWeb.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member extends ObjectTime {

    @Id
    @GeneratedValue
    @Column(name="member_num")
    private Long id;
    //멤버 아이디
    @Column(name = "member_id")
    private String memberId;
    //멤버 닉네임
    @Column(name = "member_nickname") //나중에 유니크 조건
    private String memberNickname;
    //멤버 비밀번호
    @Column(name = "password")
    private String password;
    //멤버 이름
    @Column(name = "member_name")
    private String memberName;
    //멤버 전화번호
    @Column(name = "member_phone")
    private String memberPhone;
    //멤버 이메일
    @Column(name = "email")
    private String email;

    //주소
    @Embedded
    private Address address;


    //서비스 이용약관
    @Enumerated(EnumType.STRING)
    private ServiceAgreement svcAge;


}
