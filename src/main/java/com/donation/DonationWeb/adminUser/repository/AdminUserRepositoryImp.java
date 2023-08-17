package com.donation.DonationWeb.adminUser.repository;

import com.donation.DonationWeb.domain.AdminUser;
import com.donation.DonationWeb.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AdminUserRepositoryImp implements AdminUserRepository{

    private final EntityManager em;

    @Override
    public Optional<AdminUser> findByAdminUserId(String adminUserId) {
        return em.createQuery("select a from AdminUser a where a.adminUserId = : loginId", AdminUser.class)
                .setParameter("loginId", adminUserId)
                .getResultList().stream().findAny(); //null일수도 있으므로
    }

    @Override
    public Optional<AdminUser> findById(Long adminId) {
        return Optional.ofNullable(em.find(AdminUser.class, adminId));
    }

    @Override
    public AdminUser save(AdminUser adminUser) {
        em.persist(adminUser);
        return adminUser;
    }
}
