package com.donation.DonationWeb.participant.repository;

import com.donation.DonationWeb.domain.InterestPost;
import com.donation.DonationWeb.domain.Participant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ParticipantRepositoryImp implements ParticipantRepository{

    private final EntityManager em;

    @Override
    public Optional<Participant> findByLoginIdAndPostId(Long volunteerPostId, Long loginId) {
        return  em.createQuery("select p from Participant p" +
                        " join fetch p.member m" +
                        " join fetch p.volunteerPost v" +
                        " where p.volunteerPost.id =: volunteerPost_num and p.member.id =: member_id", Participant.class)
                .setParameter("volunteerPost_num", volunteerPostId).setParameter("member_id", loginId).getResultStream().findAny();

    }

    @Override
    public void delete(Participant participant) {
        em.remove(participant);
    }

    @Override
    public void save(Participant participant) {
        em.persist(participant);
    }
}
