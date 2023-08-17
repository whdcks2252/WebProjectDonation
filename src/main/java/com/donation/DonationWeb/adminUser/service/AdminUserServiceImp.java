package com.donation.DonationWeb.adminUser.service;

import com.donation.DonationWeb.adminUser.dto.CreateAdminUserRequest;
import com.donation.DonationWeb.adminUser.repository.AdminUserRepository;
import com.donation.DonationWeb.domain.AdminUser;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.exception.UserException;
import com.donation.DonationWeb.util.BCryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminUserServiceImp implements AdminUserService{

    private final AdminUserRepository adminUserRepository;

    @Override
    public Optional<AdminUser> findByAdminUserIdAndPassword(String adminUserId, String password) {
        Optional<AdminUser> findByAdminUserId = adminUserRepository.findByAdminUserId(adminUserId);
        AdminUser adminUser = findByAdminUserId.orElseThrow(()->new UserException("not found : " + adminUserId));

        if (!findByAdminUserId.isPresent()&& BCryptor.isMatch(adminUser.getPassword(),password)) {
            return Optional.empty();
        }


        return findByAdminUserId;
    }

    @Override
    public AdminUser findById(Long AdminId) {
        return findByIdUserPresent(adminUserRepository.findById(AdminId),AdminId);
    }

    @Override
    public AdminUser save(CreateAdminUserRequest createAdminUserRequest) {
        return adminUserRepository.save(createAdminUserRequest.toEntity());
    }

    private AdminUser findByIdUserPresent(Optional<AdminUser> adminUser,Long AdminId){
        return adminUser.orElseThrow(()->new UserException("not found AdminId : " + AdminId));

    }

}
