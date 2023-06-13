package com.donation.DonationWeb.category.dto;

import com.donation.DonationWeb.domain.Category;
import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddCategoryRequest {

    private String name;

    public Category toEntity() {
            return Category.builder().name(this.name).build();
    }

}
