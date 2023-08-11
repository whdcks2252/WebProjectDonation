package com.donation.DonationWeb.kakaoPay.paymentCancel.repository;
import com.donation.DonationWeb.domain.PaymentCancel;
import com.donation.DonationWeb.domain.PaymentReady;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentCancelRepositoryJPA implements PaymentCancelRepository {
    private final EntityManager em;

    @Override
    public PaymentCancel save(PaymentCancel paymentCancel) {

        em.persist(paymentCancel);
        return paymentCancel;

    }



}
