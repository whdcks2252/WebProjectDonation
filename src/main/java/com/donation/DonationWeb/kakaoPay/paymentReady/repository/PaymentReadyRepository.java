package com.donation.DonationWeb.kakaoPay.paymentReady.repository;

import com.donation.DonationWeb.domain.PaymentReady;

import java.util.Optional;


public interface PaymentReadyRepository {

    PaymentReady save(PaymentReady paymentReady);

    Optional<PaymentReady> findWithMemberAndPostAndUUID(Long memberId,Long postId,String donationUUID);


}
