package com.donation.DonationWeb.login.dto;

import com.donation.DonationWeb.domain.Member;
import lombok.Getter;



@Getter
public class LoginResponse {

    private Long memberNum;
    private String status;
    private String memberId;
    private String memberNickname;
    private String password;
    private String memberName;
    private String memberPhone;
    private String email;

    public static LoginResponse createInstance(Member member,String status) {
        return new LoginResponse(status,member.getMemberId(),member.getId(),member.getMemberNickname(), member.getPassword(), member.getMemberName()
                ,member.getMemberPhone(),member.getEmail()

        );
    }

    public LoginResponse(String status, String memberId, Long id, String memberNickname, String password, String memberName, String memberPhone, String email) {
        this.status = status;
        this.memberId = memberId;
        this.memberNum = id;
        this.memberNickname = memberNickname;
        this.password = password;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
        this.email = email;
    }
}
