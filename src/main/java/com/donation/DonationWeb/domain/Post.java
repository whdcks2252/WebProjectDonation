package com.donation.DonationWeb.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Post {
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

    //게시물생성시간 마지막수정시간
    @Embedded
    private ObjectTime objectTime;

    //진행상태(진행중,종료)
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    //카테고리 연관관계 다대일 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categorie categorie;



}
