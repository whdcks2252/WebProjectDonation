package com.donation.DonationWeb.category.service;

import com.donation.DonationWeb.category.dto.UpdateCategoryRequest;
import com.donation.DonationWeb.category.dto.AddCategoryRequest;
import com.donation.DonationWeb.category.repository.CategoryRepository;
import com.donation.DonationWeb.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Category saveCategory(AddCategoryRequest addcategoryRequest) {
        return categoryRepository.save(addcategoryRequest.toEntity());
    }

    @Override
    public List<Category> findAll() {

        return categoryRepository.findAll();

    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    /**
     * 영속성 컨텍스트가 자동 변경
     */
    @Transactional
    @Override
    public void updateCategory(Long categoryId, UpdateCategoryRequest upCategory) {
        categoryRepository.update(categoryId, upCategory);
    }

    @Transactional
    @Override
    public void delete(Long categoryId) {
        categoryRepository.delete(categoryId);
    }
}
