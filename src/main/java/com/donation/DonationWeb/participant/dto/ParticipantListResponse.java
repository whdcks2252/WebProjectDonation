package com.donation.DonationWeb.participant.dto;

import com.donation.DonationWeb.domain.Participant;
import com.donation.DonationWeb.domain.VolunteerPost;
import com.donation.DonationWeb.volunteerPost.dto.VolunteerPostListResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParticipantListResponse {

    private Long participantId;
    private Long volunteerPostId;
    private String memberId;
    private String memberNickname;
    private String memberName;
    private String memberPhone;
    private String email;


    public static ParticipantListResponse createInstance(Participant participant) {
        return new ParticipantListResponse(participant.getId(),participant.getVolunteerPost().getId(),participant.getMember().getMemberId(),
                participant.getMember().getMemberNickname(),participant.getMember().getMemberName(),participant.getMember().getMemberPhone(),
                participant.getMember().getEmail());
    }

}
