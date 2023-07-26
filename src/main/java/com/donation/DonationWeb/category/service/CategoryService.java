package com.donation.DonationWeb.category.service;

import com.donation.DonationWeb.category.dto.UpdateCategoryRequest;
import com.donation.DonationWeb.category.dto.AddCategoryRequest;
import com.donation.DonationWeb.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public Category saveCategory(AddCategoryRequest addcategoryRequest);

    public List<Category> findAll();

    public Category findById(Long categoryId);
    public Category findByName(String categoryName);


    public void updateCategory(Long categoryId, UpdateCategoryRequest upCategory) ;

    public void delete(Long postId);
}
