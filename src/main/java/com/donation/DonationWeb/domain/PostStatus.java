package com.donation.DonationWeb.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum PostStatus {
    PROCESS("process"),
    EXPIRATION("expiration");

    /**
     *  enum 소문자로 쓰기위한 로직 직렬화 역직렬화
     */

    @Getter
    private final String value;

    PostStatus(String value) {
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
