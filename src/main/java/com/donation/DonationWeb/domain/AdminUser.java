package com.donation.DonationWeb.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class AdminUser {

    @Id
    @GeneratedValue
    @Column(name="adminUser_num")
    private Long id;

    @Column(name = "adminUser_id")
    private String adminUserId;

    @Column(name = "password")
    private String password;

    @Column(name = "adminUser_role")
    private String role;

    @Builder
    public AdminUser(String adminUserId, String password, String role) {
        this.adminUserId = adminUserId;
        this.password = password;
        this.role = role;
    }
}
