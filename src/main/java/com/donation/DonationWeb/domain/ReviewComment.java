package com.donation.DonationWeb.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class ReviewComment extends ObjectTime{
    @Id
    @GeneratedValue
    @Column(name="review_comment_num")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_num",nullable = false)
    private ReviewPost reviewPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Builder
    public ReviewComment(String content, Member member, ReviewPost reviewPost) {
        this.content = content;
        this.reviewPost = reviewPost;
        this.member = member;
    }

    public void updateComment(String content) {
        if (ObjectUtils.isEmpty(content))
            throw new IllegalArgumentException("요청 파라미터가 NULL입니다.");

        if (content != null) {
            this.content = content;

        }

    }
}
