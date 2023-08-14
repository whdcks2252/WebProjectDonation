package com.donation.DonationWeb.volunteerComment.repository;

import com.donation.DonationWeb.domain.ReviewComment;
import com.donation.DonationWeb.domain.VolunteerComment;
import com.donation.DonationWeb.reviewComment.dto.UpdateReviewCommentRequest;
import com.donation.DonationWeb.volunteerComment.dto.UpdateVolunteerCommentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class VolunteerCommentRepositoryJpa implements VolunteerCommentRepository {

    private final EntityManager em;
    @Override
    public VolunteerComment save(VolunteerComment volunteerComment) {
        em.persist(volunteerComment);
        return volunteerComment;
    }

    @Override
    public void delete(VolunteerComment volunteerComment) {
        em.remove(volunteerComment);

    }

    @Override
    public void update(VolunteerComment volunteerComment, UpdateVolunteerCommentRequest updateVolunteerCommentRequest) {

        volunteerComment.updateComment(updateVolunteerCommentRequest.getContent());
    }



    @Override
    public Optional<VolunteerComment> findById(Long commentId) {
        return Optional.ofNullable(em.find(VolunteerComment.class, commentId));

    }
}
