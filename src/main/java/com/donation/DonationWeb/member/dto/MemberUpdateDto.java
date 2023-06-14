package com.donation.DonationWeb.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateDto {
    private String memberNickname;
    private String memberPhone;
    private String email;
    private String city;
    private String street;
    private String zipcode;

}
