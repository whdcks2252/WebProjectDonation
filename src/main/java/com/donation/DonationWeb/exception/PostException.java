package com.donation.DonationWeb.exception;

public class PostException extends IllegalArgumentException {

    public PostException() {
        super();
    }

    public PostException(String message) {
        super(message);
    }

    public PostException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }


}
