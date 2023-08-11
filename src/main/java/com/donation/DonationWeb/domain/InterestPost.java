package com.donation.DonationWeb.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
//관심 게시판

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class InterestPost extends ObjectTime{

    @Id
    @GeneratedValue
    @Column(name="post_inter_num")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_num",nullable = false)
   private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteer_num",nullable = false)
    private VolunteerPost volunteerPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_num",nullable = false)
    private ReviewPost reviewPost;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public InterestPost(Post post, Member member, VolunteerPost volunteerPost, ReviewPost reviewPost) {

        this.post = post;
        this.volunteerPost = volunteerPost;
        this.reviewPost = reviewPost;
        this.member = member;
    }
}
