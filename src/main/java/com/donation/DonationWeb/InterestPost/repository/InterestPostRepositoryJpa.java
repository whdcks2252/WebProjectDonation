package com.donation.DonationWeb.InterestPost.repository;
import com.donation.DonationWeb.domain.InterestPost;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class InterestPostRepositoryJpa implements InterestPostRepository {

    private final EntityManager em;

    @Override
    public void save(InterestPost interestPost) {
         em.persist(interestPost);

    }

    @Override
    public void delete(InterestPost interestPost) {
        em.remove(interestPost);

    }

    @Override
    public Optional<InterestPost> findbyLoginIdAndPostId(Long postId, Long loginId) {
     return  em.createQuery("select i from InterestPost i" +
                     " join fetch i.member m" +
                     " join fetch i.post p" +
                     " where i.post.id =: post_num and i.member.id =: member_id",InterestPost.class)
                .setParameter("post_num", postId).setParameter("member_id", loginId).getResultStream().findAny();



    }


}
