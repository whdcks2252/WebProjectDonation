package com.donation.DonationWeb.service;

import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.post.repository.PostRepositoryImp;
import com.donation.DonationWeb.post.service.PostServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired
    PostRepositoryImp postRepository;
    @Autowired
    PostServiceImp postService;
    TransactionStatus status;
    @Autowired
    PlatformTransactionManager transactionManager;

    @BeforeEach
    void  beforeEach() {
        //트랜잭션 시작
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
    }


    @Test
    public void 게시물등록() throws Exception {

       //given

        //when

        //then



    }
}
