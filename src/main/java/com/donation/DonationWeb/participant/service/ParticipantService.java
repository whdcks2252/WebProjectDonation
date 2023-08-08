package com.donation.DonationWeb.participant.service;

import com.donation.DonationWeb.domain.InterestPost;
import com.donation.DonationWeb.domain.Participant;

import java.util.Optional;

public interface ParticipantService {
    public Optional<Boolean> participantPut(Long volunteerPostId, Long loginId);

    public Participant findByLoginIdAndPostId(Long volunteerPostId, Long loginId);
}
