package com.donation.DonationWeb.member.dto;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.ServiceAgreement;
import lombok.Getter;

@Getter
public class AddMemberResponse {
    private String memberId;
    //멤버 닉네임
    private String memberNickname;
    //멤버 이름
    private String memberName;
    //멤버 전화번호
    private String memberPhone;
    //멤버 이메일
    private String email;
    //이용 약관
    private ServiceAgreement serviceAgreement;
    //address
    private String city;
    private String street;
    private String zipcode;

    public static AddMemberResponse createInstance(Member member) {
        return new AddMemberResponse(member.getMemberId(),member.getMemberNickname(),member.getMemberName()
                ,member.getMemberPhone(),member.getEmail(), member.getSvcAge(),
                member.getAddress().getCity(),member.getAddress().getStreet(), member.getAddress().getZipcode()
               );
    }

    public  AddMemberResponse (String memberId,String memberNickname,String memberName,
                               String memberPhone,String email,ServiceAgreement serviceAgreement,
                               String city,String street,String zipcode
                             ){
        this.memberId = memberId;
        this.memberNickname = memberNickname;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.email = email;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.serviceAgreement = serviceAgreement;
    }
}