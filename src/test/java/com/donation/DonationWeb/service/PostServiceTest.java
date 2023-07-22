package com.donation.DonationWeb.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@SpringBootTest
@Transactional
public class PostServiceTest {

    @PersistenceContext
    EntityManager em;


    @BeforeEach

    void beforeEach() {
        //트랜잭션 시작
    }


    @Test
    public void 게시물등록() throws Exception {




    }
}
