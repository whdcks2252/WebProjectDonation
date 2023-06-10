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

    @Column(name = "post_cre_at")
    private LocalDateTime createTime;

    @Column(name = "post_up_time")
    private LocalDateTime updateTime;

    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;



}
