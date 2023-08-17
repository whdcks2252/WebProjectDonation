package com.donation.DonationWeb.adminUser.dto;

import com.donation.DonationWeb.domain.AdminUser;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.login.dto.LoginResponse;
import lombok.Getter;

@Getter
public class LoginAdminUserResponse {
    private Long id;
    private String adminUserId;
    private String password;
    private String role;
    private String status;

    public static LoginAdminUserResponse createInstance(AdminUser adminUser, String status) {
        return new LoginAdminUserResponse(adminUser.getId(),adminUser.getAdminUserId(), adminUser.getPassword(), adminUser.getRole()
                ,status);
    }

    public LoginAdminUserResponse(Long id, String adminUserId, String password, String role, String status) {
        this.id = id;
        this.adminUserId = adminUserId;
        this.password = password;
        this.role = role;
        this.status = status;
    }
}
