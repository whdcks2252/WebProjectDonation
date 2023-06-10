package com.donation.DonationWeb.Domain;


import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "post_num")
    private Long id;


    //생성시간
    @Column(name = "post_cre_at")
    private LocalDateTime createTime;
    //마지막 수정 시간
    @Column(name = "post_up_time")
    private LocalDateTime updateTime;
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

    @Column(name="out_url")
    //외부링크
    private String outUrl;



}
