package com.donation.DonationWeb.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Result <T>{
    private T data;


}
