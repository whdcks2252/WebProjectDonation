package com.donation.DonationWeb.domain;


import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.ObjectUtils;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Post extends ObjectTime {
    @Builder
    public Post(String title, String content, PostStatus postStatus, Category categorie, Member member, Integer targetAmount,Integer currentAmount ) {
        this.title = title;
        this.content = content;
        this.postStatus = postStatus;
        this.categorie = categorie;
        this.member = member;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
    }

    @Id
    @GeneratedValue
    @Column(name = "post_num")
    private Long id;

    //제목
    @Column(length = 500)
    private String title;

    //텍스트
    @Column(name = "post_content")
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

    @OneToMany(mappedBy = "post")
    private List<Comment> commemts = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<InterestPost> interestPosts = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    private List<Payment> payments = new ArrayList<>();


    @Column(name = "target_amount")
    private Integer targetAmount;
    @Column(name = "current_amount")
    private Integer currentAmount;

    //업데이트 null검증 상태만 null 가능 검증

    public void CategoryChangeAndUpdateValidate(UpdatePostRequest updatePostRequest, Category categorie) {
        if (ObjectUtils.isEmpty(updatePostRequest))
            throw new IllegalArgumentException("요청 파라미터가 NULL입니다.");
        if (updatePostRequest.getTitle() != null) {
            this.title = updatePostRequest.getTitle();
        }
        if (updatePostRequest.getContent() != null) {
            this.content = updatePostRequest.getContent();
        }
        if (updatePostRequest.getPostStatus() != null) {
            this.postStatus = updatePostRequest.getPostStatus();
        }
        if (categorie != null) {
            this.categorie = categorie;

        }

    }
    public void updateCurrentAmount(Integer updateAmount) {
        this.currentAmount += updateAmount;
    }


}
