package com.donation.DonationWeb.exception;

public class MemberAuthorityRequestException extends IllegalArgumentException{
    public MemberAuthorityRequestException() {
        super();
    }

    public MemberAuthorityRequestException(String s) {
        super(s);
    }

    public MemberAuthorityRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberAuthorityRequestException(Throwable cause) {
        super(cause);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
