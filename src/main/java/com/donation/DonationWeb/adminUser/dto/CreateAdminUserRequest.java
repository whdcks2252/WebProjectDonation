package com.donation.DonationWeb.adminUser.dto;

import com.donation.DonationWeb.domain.AdminUser;
import com.donation.DonationWeb.util.BCryptor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateAdminUserRequest {

    @NotBlank
    private String adminUserId;
    @NotBlank
    private String password;
    @NotBlank
    private String role;

    public CreateAdminUserRequest(String adminUserId, String password, String role) {
        this.adminUserId = adminUserId;
        this.password = password;
        this.role = role;
    }

    public AdminUser toEntity( ) {
        return AdminUser.builder()
                .adminUserId(this.adminUserId)
                .password(BCryptor.encrypt(this.password)).role(this.role)
                .build();
    }
}
