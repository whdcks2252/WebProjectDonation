package com.donation.DonationWeb.login.service;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.login.dto.LoginMemberRequest;
import com.donation.DonationWeb.member.dto.AddMemberRequest;

import java.util.Optional;

public interface LoginService {

    void loginUser(Long id);

    void logoutUser();

    Long getCurrentUser();


}
