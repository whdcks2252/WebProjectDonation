package com.donation.DonationWeb.domain;


import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Post extends ObjectTime{
    @Builder
    public Post(String title, String content,PostStatus postStatus) {
        this.title = title;
        this.content = content;
        this.postStatus = postStatus;
    }
    @Id
    @GeneratedValue
    @Column(name = "post_num")
    private Long id;

    //제목
    @Column(length = 500,nullable = false)
    private String title;

    //텍스트
    @Column(name="post_content", nullable = false)
    @Lob
    private String content;


    //진행상태(진행중,종료)
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    //카테고리 연관관계 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category categorie;


    //업데이트 null검증
    public   void updateValidate(UpdatePostRequest updatePostRequest) {
        if(ObjectUtils.isEmpty(updatePostRequest))
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
    }

}
