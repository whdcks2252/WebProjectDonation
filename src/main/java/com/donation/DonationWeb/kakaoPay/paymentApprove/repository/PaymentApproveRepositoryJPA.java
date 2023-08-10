package com.donation.DonationWeb.kakaoPay.paymentApprove.repository;
import com.donation.DonationWeb.domain.PaymentApprove;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentApproveRepositoryJPA implements PaymentApproveRepository {
    private final EntityManager em;

    @Override
    public PaymentApprove save(PaymentApprove paymentApprove) {

        em.persist(paymentApprove);
        return paymentApprove;

    }

    @Override
    public Optional<PaymentApprove> findById(Long paymentId) {
        return Optional.ofNullable(em.find(PaymentApprove.class, paymentId));

    }



}
