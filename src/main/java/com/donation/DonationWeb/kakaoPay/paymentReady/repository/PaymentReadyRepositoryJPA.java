package com.donation.DonationWeb.kakaoPay.paymentReady.repository;
import com.donation.DonationWeb.domain.PaymentReady;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PaymentReadyRepositoryJPA implements PaymentReadyRepository {
    private final EntityManager em;

    @Override
    public PaymentReady save(PaymentReady paymentReady) {

        em.persist(paymentReady);
        return paymentReady;

    }

    @Override
    public Optional<PaymentReady> findWithMemberAndPostAndUUID(Long memberId, Long postId, String donationUUID) {
       return em.createQuery("select p from PaymentReady p" +
                       " join fetch p.post po" +
                       " join fetch p.member m" +
                       " where p.member.id=:memberId and p.post.id=:postId and p.donationUUID=:donationUUID", PaymentReady.class)
                .setParameter("memberId", memberId).setParameter("postId", postId).setParameter("donationUUID", donationUUID)
               .getResultStream().findAny();
    }


}
