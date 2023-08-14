package com.donation.DonationWeb.category.repository;

import com.donation.DonationWeb.category.dto.UpdateCategoryRequest;
import com.donation.DonationWeb.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImp implements CategoryRepository {

    private final EntityManager em;

    @Override
    public Category save(Category category) {
        em.persist(category);
        return category;
    }

    @Override
    public void update(Long categoryId, UpdateCategoryRequest updateCategoryRequest) {
        Category findCategory = findById(categoryId).orElseThrow(() -> new IllegalArgumentException("not found : " + categoryId));
        findCategory.updateValidate(updateCategoryRequest);
    }

    @Override
    public void delete(Long categoryId) {
        Category findCategory = findById(categoryId).orElseThrow(() -> new IllegalArgumentException("not found : " + categoryId));
        em.remove(findCategory);
    }

    @Override
    public Optional<Category> findById(Long categoryId) {
        Category category = em.find(Category.class, categoryId);
        return Optional.ofNullable(category);
    }

    @Override
    public List<Category> findAll() {
        return em.createQuery("select c from Category c",Category.class).getResultList();
    }

    @Override
    public Optional<Category> findByName(String categoryName) {
        return em.createQuery("select c from Category c " +
                        "where c.CategoryName= :Category_name",Category.class).
                setParameter("Category_name", categoryName).getResultStream().findAny();

    }
}
