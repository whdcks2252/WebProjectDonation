package com.donation.DonationWeb.member.dto;

import com.donation.DonationWeb.domain.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor
public class AddMemberRequest {

    private String memberId;
    //멤버 닉네임
    @NotBlank
    private String memberNickname;
    @NotBlank
    private String password;
    @NotBlank
    //멤버 이름
    private String memberName;
    @NotBlank
    //멤버 전화번호
    private String memberPhone;

    @NotBlank
    @Email
    //멤버 이메일
    private String email;
    //이용 약관
    private ServiceAgreement serviceAgreement;
    //address
    private String city;
    private String street;
    private String zipcode;

    public AddMemberRequest(String memberId, String memberNickname, String password,
                            String memberName, String memberPhone, String email, String city,String street,String zipcode,ServiceAgreement serviceAgreement) {
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.password = password;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.email = email;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.serviceAgreement = serviceAgreement;
    }

    public Member toEntity( ) {
        return Member.builder()
                .memberId(this.memberId).memberNickname(this.memberNickname)
                .password(this.password).memberName(this.memberName).memberPhone(this.memberPhone)
                .email(this.email).svcAge(serviceAgreement).address(new Address(this.city,this.street,this.zipcode))
                .build();
    }

}
