package com.donation.DonationWeb.adminUser.repository;

import com.donation.DonationWeb.domain.AdminUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AdminUserRepository {
    Optional<AdminUser> findByAdminUserId(String adminUserId);


    Optional<AdminUser> findById(Long adminId);

    AdminUser save(AdminUser adminUser);
}
