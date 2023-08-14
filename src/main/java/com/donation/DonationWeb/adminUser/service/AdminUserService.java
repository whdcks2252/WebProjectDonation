package com.donation.DonationWeb.adminUser.service;

import com.donation.DonationWeb.adminUser.dto.CreateAdminUserRequest;
import com.donation.DonationWeb.domain.AdminUser;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.member.dto.AddMemberRequest;

import java.util.Optional;

public interface AdminUserService {
    Optional<AdminUser> findByAdminUserIdAndPassword(String adminUserId, String password);

    AdminUser findById(Long AdminId);

    AdminUser save(CreateAdminUserRequest createAdminUserRequest);

}
