package com.donation.DonationWeb.exception;

public class CommentException extends IllegalArgumentException {

    public CommentException() {
        super();
    }

    public CommentException(String message) {
        super(message);
    }

    public CommentException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }


}
