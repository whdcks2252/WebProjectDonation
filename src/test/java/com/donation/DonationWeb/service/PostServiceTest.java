package com.donation.DonationWeb.service;

import com.donation.DonationWeb.repository.PostRepositoryImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired
    PostRepositoryImp postRepository;
    @Autowired
    PostService postService;
    @Test
    public void 게시물등록() throws Exception {





    }
}
