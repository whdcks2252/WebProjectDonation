package com.donation.DonationWeb.kakaoPay.paymentApprove.repository;

import com.donation.DonationWeb.domain.PaymentApprove;

import java.util.Optional;


public interface PaymentApproveRepository {

    PaymentApprove save(PaymentApprove paymentApprove);

    Optional<PaymentApprove> findById(Long paymentId);


}
