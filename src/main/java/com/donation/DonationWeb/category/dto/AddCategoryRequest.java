package com.donation.DonationWeb.category.dto;

import com.donation.DonationWeb.domain.Category;
import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddCategoryRequest {

    private String categoryName;

    public Category toEntity() {
            return Category.builder().CategoryName(this.categoryName).build();
    }

}
