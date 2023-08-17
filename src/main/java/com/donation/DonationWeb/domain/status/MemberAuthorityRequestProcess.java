package com.donation.DonationWeb.domain.status;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum MemberAuthorityRequestProcess {
    APPROVE ("approve"),
    WAIT("wait"),
    REJECT("reject");

    /**
     *  enum 소문자로 쓰기위한 로직 직렬화 역직렬화
     */

    @Getter
    private final String value;

    MemberAuthorityRequestProcess(String value) {
        this.value = value;
    }

    @JsonCreator
    public static MemberAuthorityRequestProcess from(String value) {
        for (MemberAuthorityRequestProcess status : MemberAuthorityRequestProcess.values()) {
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

