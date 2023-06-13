package com.donation.DonationWeb.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class ObjectTime {
    //생성시간 업데이트 시간
    public ObjectTime() {
    }

    public ObjectTime(LocalDateTime createTime, LocalDateTime updateTime) {
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
    @CreatedDate
    //생성 시간
    private LocalDateTime createTime;
    @LastModifiedDate
    //마지막 수정시간
    private LocalDateTime updateTime;
}
