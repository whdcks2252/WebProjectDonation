package com.donation.DonationWeb.kakaoPay.paymentReady.repository;
import com.donation.DonationWeb.domain.PaymentReady;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
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
                       " join fetch p.member m"+
                       " join fetch p.post po"+
                       " where p.member.id=:memberId and p.donationUUID=:donationUUID and p.post.id=: postId", PaymentReady.class)
                .setParameter("memberId", memberId).setParameter("donationUUID", donationUUID).setParameter("postId",postId)
               .getResultStream().findAny();
    }


}
