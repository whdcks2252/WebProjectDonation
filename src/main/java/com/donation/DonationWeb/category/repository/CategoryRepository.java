package com.donation.DonationWeb.category.repository;

import com.donation.DonationWeb.category.dto.UpdateCategoryRequest;
import com.donation.DonationWeb.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category save(Category category);

    void update(Long categoryId, UpdateCategoryRequest updateCategoryRequest);

    void delete(Long categoryId);

    Optional<Category> findById(Long categoryId);

    List<Category> findAll();

    public Optional<Category> findByName(String categoryName);
}
