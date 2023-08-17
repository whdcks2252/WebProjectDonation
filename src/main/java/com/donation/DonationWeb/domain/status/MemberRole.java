package com.donation.DonationWeb.domain.status;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum MemberRole {
    AUTHORIZED ("authorized"),
    NON_AUTHORIZED("non_authorized");

    /**
     *  enum 소문자로 쓰기위한 로직 직렬화 역직렬화
     */

    @Getter
    private final String value;

    MemberRole(String value) {
        this.value = value;
    }

    @JsonCreator
    public static MemberRole from(String value) {
        for (MemberRole status : MemberRole.values()) {
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
