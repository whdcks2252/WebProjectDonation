package com.donation.DonationWeb.volunteerPost.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Result<T>{
    private T data;

   public static <T> T createInstance(T data){
       return (T) new Result(data);
    }

}
