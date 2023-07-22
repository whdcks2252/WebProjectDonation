package com.donation.DonationWeb.login.service;


import java.util.Optional;

public interface LoginService {

    void loginUser(Long id);

    void logoutUser();

    Long getCurrentUser();


}
