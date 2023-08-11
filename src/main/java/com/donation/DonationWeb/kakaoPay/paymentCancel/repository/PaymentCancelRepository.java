package com.donation.DonationWeb.kakaoPay.paymentCancel.repository;

import com.donation.DonationWeb.domain.PaymentCancel;
import com.donation.DonationWeb.domain.PaymentReady;

import java.util.Optional;


public interface PaymentCancelRepository {

    PaymentCancel save(PaymentCancel paymentCancel);



}
