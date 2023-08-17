package com.donation.DonationWeb.domain;

import com.donation.DonationWeb.reviewPost.dto.UpdateReviewPostRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class ReviewPost extends ObjectTime{

    @Id
    @GeneratedValue
    @Column(name = "review_num")
    private Long id;

    //제목
    @Column(length = 500)
    private String title;

    //텍스트
    @Column(name = "review_content")
    @Lob
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_num",foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Post post;

    //카테고리 연관관계 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false,foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Category categorie;

    @OneToMany(mappedBy = "reviewPost")
    private List<ReviewComment> reviewComments = new ArrayList<>();

    @Builder
    public ReviewPost(String title, String content, Post post, Category categorie) {
        this.title = title;
        this.content = content;
        this.post = post;
        this.categorie = categorie;
    }

    public void CategoryChangeAndUpdateValidate(UpdateReviewPostRequest request, Category categorie, Post post) {
        if (ObjectUtils.isEmpty(request))
            throw new IllegalArgumentException("요청 파라미터가 NULL입니다.");
        if (request.getTitle() != null) {
            this.title = request.getTitle();
        }
        if (request.getContent() != null) {
            this.content = request.getContent();
        }
        if (categorie != null) {
            this.categorie = categorie;
        }
        if (post != null){
            this.post = post;
        }

    }
}

