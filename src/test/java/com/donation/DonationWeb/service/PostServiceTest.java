package com.donation.DonationWeb.service;

import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.repository.PostRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    PostService postService;
    @Test
    public void 게시물등록() throws Exception {





    }
}
