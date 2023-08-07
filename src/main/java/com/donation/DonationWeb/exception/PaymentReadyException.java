package com.donation.DonationWeb.exception;

public class PaymentReadyException extends IllegalArgumentException {

    public PaymentReadyException() {
        super();
    }

    public PaymentReadyException(String message) {
        super(message);
    }

    public PaymentReadyException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaymentReadyException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }


}
