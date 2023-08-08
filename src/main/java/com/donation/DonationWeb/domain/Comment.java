package com.donation.DonationWeb.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.util.List;

//댓글
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Getter
public class Comment extends ObjectTime{
    @Id
    @GeneratedValue
    @Column(name="comment_num")
    private Long id;

    private String content;

    @ManyToOne
    @JoinColumn(name = "post_num",nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "volunteer_num",nullable = false)
    private VolunteerPost volunteerPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;



    @Builder
    public Comment(String content, Post post, Member member, VolunteerPost volunteerPost) {
        this.content = content;
        this.post = post;
        this.volunteerPost = volunteerPost;
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
