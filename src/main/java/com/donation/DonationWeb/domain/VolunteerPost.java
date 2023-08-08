package com.donation.DonationWeb.domain;

import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import com.donation.DonationWeb.volunteerPost.dto.UpdateVolunteerPostRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class VolunteerPost extends ObjectTime {

    @Builder
    public VolunteerPost(String title, String content, PostStatus postStatus, Category categorie, Member member, List<Comment> commemts, List<InterestPost> interestPosts, Integer needAmount, Integer currentParticipantAmount) {
        this.title = title;
        this.content = content;
        this.postStatus = postStatus;
        this.categorie = categorie;
        this.member = member;
        this.commemts = commemts;
        this.interestPosts = interestPosts;
        this.needAmount = needAmount;
        this.currentParticipantAmount = currentParticipantAmount;
    }

    @Id
    @GeneratedValue
    @Column(name = "volunteer_num")
    private Long id;

    //제목
    @Column(length = 500)
    private String title;

    //텍스트
    @Column(name = "volunteer_content")
    @Lob
    private String content;

    //진행상태(진행중,종료)
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    //카테고리 연관관계 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    private Category categorie;

    //카테고리 연관관계 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "volunteerPost")
    private List<Participant> participants = new ArrayList<>();

    @OneToMany(mappedBy = "volunteerPost")
    private List<Comment> commemts = new ArrayList<>();

    @OneToMany(mappedBy = "volunteerPost")
    private List<InterestPost> interestPosts = new ArrayList<>();

    @Column(name = "need_amount")
    private Integer needAmount;

    @Column(name = "current_participant_amount")
    private Integer currentParticipantAmount;

    public void CategoryChangeAndUpdateValidate(UpdateVolunteerPostRequest request, Category categorie) {
        if (ObjectUtils.isEmpty(request))
            throw new IllegalArgumentException("요청 파라미터가 NULL입니다.");
        if (request.getTitle() != null) {
            this.title = request.getTitle();
        }
        if (request.getContent() != null) {
            this.content = request.getContent();
        }
        if (request.getPostStatus() != null) {
            this.postStatus = request.getPostStatus();
        }
        if (categorie != null) {
            this.categorie = categorie;

        }

    }

    public void UpdateCurrentParticipantAmount(Integer currentParticipantAmount){
        this.currentParticipantAmount = currentParticipantAmount;

        if(currentParticipantAmount>=needAmount) postStatus = PostStatus.EXPIRATION;
    }

}
