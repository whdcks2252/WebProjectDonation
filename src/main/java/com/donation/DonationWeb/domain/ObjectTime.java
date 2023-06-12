package com.donation.DonationWeb.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
@Embeddable
@Getter
public class ObjectTime {
    public ObjectTime() {
    }

    public ObjectTime(LocalDateTime createTime, LocalDateTime updateTime) {
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    //생성 시간
    private LocalDateTime createTime;
    //마지막 수정시간
    private LocalDateTime updateTime;
}
