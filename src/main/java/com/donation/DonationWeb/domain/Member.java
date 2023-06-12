package com.donation.DonationWeb.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Member {

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

    //생성시간, 마지막수정시간
    @Embedded
    private ObjectTime objectTime;

    //서비스 이용약관
    @Enumerated(EnumType.STRING)
    private ServiceAgreement svcAge;


}
