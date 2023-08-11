package com.donation.DonationWeb.adminUser.service;

import com.donation.DonationWeb.domain.AdminUser;
import com.donation.DonationWeb.domain.Member;

import java.util.Optional;

public interface AdminUserService {
    Optional<AdminUser> findByAdminUserIdAndPassword(String adminUserId, String password);

    Member findById(Long memberId);

}
