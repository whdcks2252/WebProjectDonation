package com.donation.DonationWeb.exception;

public class KakaoPayException extends RuntimeException {
    public KakaoPayException() {
        super();
    }

    public KakaoPayException(String message) {
        super(message);
    }

    public KakaoPayException(String message, Throwable cause) {
        super(message, cause);
    }

    public KakaoPayException(Throwable cause) {
        super(cause);
    }

    protected KakaoPayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
