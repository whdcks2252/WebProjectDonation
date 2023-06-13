package com.donation.DonationWeb.domain;

import com.donation.DonationWeb.category.dto.UpdateCategoryRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Category extends ObjectTime {
    @Id @GeneratedValue
    @Column(name= "category_id")
    private Long id;

    private String name;
    @Builder
    public Category(String name) {
        this.name = name;
    }


    //update 검증
    public  void updateValidate(UpdateCategoryRequest updateCategoryRequest) {
        if(ObjectUtils.isEmpty(updateCategoryRequest))
            throw new IllegalArgumentException("요청 파라미터가 NULL입니다.");
        if (updateCategoryRequest != null) {
            this.name = updateCategoryRequest.getName();
        }

    }
}
