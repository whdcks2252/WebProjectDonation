package com.donation.DonationWeb.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Participant extends ObjectTime{
    @Id
    @GeneratedValue
    @Column(name = "participant_num")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteer_num",nullable = false,foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private VolunteerPost volunteerPost;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false,foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Member member;

    @Builder
    public Participant(VolunteerPost volunteerPost, Member member) {
        this.volunteerPost = volunteerPost;
        this.member = member;
    }
}
