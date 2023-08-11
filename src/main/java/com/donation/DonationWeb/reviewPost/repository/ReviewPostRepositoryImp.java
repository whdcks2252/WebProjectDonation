package com.donation.DonationWeb.reviewPost.repository;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.ReviewPost;
import com.donation.DonationWeb.domain.VolunteerPost;
import com.donation.DonationWeb.reviewPost.dto.UpdateReviewPostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ReviewPostRepositoryImp implements ReviewPostRepository{

    private final EntityManager em;

    @Override
    public ReviewPost save(ReviewPost reviewPost) {
        em.persist(reviewPost);
        return reviewPost;
    }

    @Override
    public Optional<ReviewPost> findById(Long reviewPostId) {
        ReviewPost reviewPost = em.find(ReviewPost.class, reviewPostId);
        return Optional.ofNullable(reviewPost);
    }

    @Override
    public Optional<ReviewPost> findByIdLeftJoin(Long reviewPostId) {
        return em.createQuery("select r from ReviewPost r" +
                        " join fetch r.member m" +
                        " join fetch r.categorie c" +
                        " join fetch r.post p" +
                        " left join fetch r.commemts co" +
                        " where r.id=:reviewPostId ", ReviewPost.class) //레프트 조인해도 postId 값을 주니까 괜찮음
                .setParameter("reviewPostId", reviewPostId).getResultList().stream().findAny();
    }

    @Override
    public List<ReviewPost> findByPage(Integer page) {
        if(page<=0) {
            page=page+1;
        }
        return em.createQuery("select r from ReviewPost r" +
                        " join fetch r.member m" +
                        " join fetch r.categorie c" +
                        " join fetch r.post p" +
                        " order by r.createTime DESC ", ReviewPost.class)
                .setFirstResult((page - 1) * 10)
                .setMaxResults(page*10)
                .getResultList();
    }

    @Override
    public List<ReviewPost> findByCategory(Long categoryId, Integer page) {
        if(page<=0) {
            page=page+1;
        }
        log.info("Page={}",page);
        return em.createQuery("select r from ReviewPost r" +
                        " join fetch r.member m" +
                        " join fetch r.categorie c" +
                        " join fetch r.post p" +
                        " where r.categorie.id=:category_id" +
                        " order by r.createTime DESC ",ReviewPost.class)
                .setParameter("category_id",categoryId)
                .setFirstResult((page-1)*10)
                .setMaxResults(page*10)
                .getResultList();
    }

    @Override
    public Optional<ReviewPost> findByPostTitle(Long postId) {
        return em.createQuery("select r from ReviewPost r" +
                        " join fetch r.member m" +
                        " join fetch r.categorie c" +
                        " join fetch r.post p" +
                        " left join fetch r.commemts co" +
                        " where r.post.id=:postId ", ReviewPost.class) //레프트 조인해도 postId 값을 주니까 괜찮음
                .setParameter("postId", postId).getResultList().stream().findAny();
    }

    @Override
    public void update(UpdateReviewPostRequest request, ReviewPost reviewPost, Category category, Post post) {
        reviewPost.CategoryChangeAndUpdateValidate(request,category,post);
    }


    @Override
    public void delete(ReviewPost reviewPost) {
        em.remove(reviewPost);
    }
}
