package com.donation.DonationWeb.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum ServiceAgreement {
    YES("yes"),NO("no");


    @Getter
    private final String value;

    ServiceAgreement(String value) {
        this.value = value;
    }

    @JsonCreator
    public static ServiceAgreement from(String value) {
        for (ServiceAgreement status : ServiceAgreement.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}