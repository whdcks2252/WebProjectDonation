package com.donation.DonationWeb.volunteerPost.repository;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Participant;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.VolunteerPost;
import com.donation.DonationWeb.volunteerPost.dto.UpdateVolunteerPostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class VolunteerPostRepositoryImp implements VolunteerPostRepository{

    private final EntityManager em;

    @Override
    public VolunteerPost save(VolunteerPost volunteerPost) {
        em.persist(volunteerPost);
        return volunteerPost;
    }

    @Override
    public Optional<VolunteerPost> findById(Long volunteerPostId) {
        VolunteerPost volunteerPost = em.find(VolunteerPost.class, volunteerPostId);
        return Optional.ofNullable(volunteerPost);
    }

    @Override
    public Optional<VolunteerPost> findByIdLeftJoin(Long volunteerPostId) {
        return em.createQuery("select v from VolunteerPost v" +
                        " join fetch v.member m" +
                        " join fetch v.categorie c" +
                        " left join fetch v.volunteerComments co" +
                        " where v.id=:volunteerPostId ", VolunteerPost.class) //레프트 조인해도 postId 값을 주니까 괜찮음
                .setParameter("volunteerPostId", volunteerPostId).getResultList().stream().findAny();
    }

    @Override
    public List<VolunteerPost> findByPage(Integer page) {
        if(page<=0) {
            page=page+1;
        }
        return em.createQuery("select v from VolunteerPost v" +
                                " join fetch v.member m" +
                                " join fetch v.categorie c" +
                                " order by v.createTime DESC ", VolunteerPost.class)
                .setFirstResult((page - 1) * 10)
                .setMaxResults(page*10)
                .getResultList();
    }

    @Override
    public List<VolunteerPost> findByCategory(Long categoryId, Integer page) {
        if(page<=0) {
            page=page+1;
        }
        log.info("Page={}",page);
        return em.createQuery("select v from VolunteerPost v" +
                                " join fetch v.member m" +
                                " join fetch v.categorie c" +
                                " where v.categorie.id=:category_id" +
                                " order by v.createTime DESC ",VolunteerPost.class)
                .setParameter("category_id",categoryId)
                .setFirstResult((page-1)*10)
                .setMaxResults(page*10)
                .getResultList();
    }

    @Override
    public void update(UpdateVolunteerPostRequest request, VolunteerPost volunteerPost, Category category) {
        volunteerPost.CategoryChangeAndUpdateValidate(request,category);
    }

    @Override
    public void delete(VolunteerPost volunteerPost) {
        em.remove(volunteerPost);
    }

    @Override
    public List<VolunteerPost> findParticipants(Long volunteerPostId) {
        return em.createQuery(
                        "select v from VolunteerPost v" +
                                " join fetch v.participants p" +
                                " where v.id=:volunteerPostId ", VolunteerPost.class)
                .setParameter("volunteerPostId", volunteerPostId).getResultList();
    }
}
