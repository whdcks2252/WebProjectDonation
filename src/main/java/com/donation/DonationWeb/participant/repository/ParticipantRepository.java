package com.donation.DonationWeb.participant.repository;

import com.donation.DonationWeb.domain.Participant;

import java.util.List;
import java.util.Optional;

public interface ParticipantRepository {
    Optional<Participant> findByLoginIdAndPostId(Long volunteerPostId, Long loginId);

    void delete(Participant participant);

    void save(Participant participant);

    List<Participant> findByIdPage(Long volunteerPostId, Integer page);
}
