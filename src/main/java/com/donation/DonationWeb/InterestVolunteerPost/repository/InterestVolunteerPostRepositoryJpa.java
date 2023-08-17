package com.donation.DonationWeb.InterestVolunteerPost.repository;

import com.donation.DonationWeb.domain.InterestVolunteerPost;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class InterestVolunteerPostRepositoryJpa implements InterestVolunteerPostRepository {

    private final EntityManager em;

    @Override
    public void save(InterestVolunteerPost interestVolunteerPost) {
         em.persist(interestVolunteerPost);

    }

    @Override
    public void delete(InterestVolunteerPost interestVolunteerPost) {
        em.remove(interestVolunteerPost);

    }

    @Override
    public Optional<InterestVolunteerPost> findByLoginIdAndPostId(Long postId, Long loginId) {
     return  em.createQuery("select i from InterestVolunteerPost i" +
                     " join fetch i.member m" +
                     " join fetch i.volunteerPost p" +
                     " where i.volunteerPost.id =: volunteerPost_num and i.member.id =: member_id",InterestVolunteerPost.class)
                .setParameter("volunteerPost_num", postId).setParameter("member_id", loginId).getResultStream().findAny();



    }


}
