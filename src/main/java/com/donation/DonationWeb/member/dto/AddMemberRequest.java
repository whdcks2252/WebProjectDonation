package com.donation.DonationWeb.member.dto;

import com.donation.DonationWeb.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class AddMemberRequest {


    private String memberId;
    //멤버 닉네임
    private String memberNickname;

    private String password;
    //멤버 이름
    private String memberName;
    //멤버 전화번호
    private String memberPhone;
    //멤버 이메일
    private String email;
    //이용 약관
    private ServiceAgreement serviceAgreement;

    public AddMemberRequest(String memberId, String memberNickname, String password, String memberName, String memberPhone, String email) {
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.password = password;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.email = email;
    }

    public Member toEntity(ServiceAgreement serviceAgreement) {
        return Member.builder()
                .memberId(this.memberId).memberNickname(this.memberNickname)
                .password(this.password).memberName(this.memberName).memberPhone(this.memberPhone)
                .email(this.email).svcAge(serviceAgreement)
                .build();
    }

}
