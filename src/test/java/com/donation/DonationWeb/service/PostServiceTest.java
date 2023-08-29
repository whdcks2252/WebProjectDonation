package com.donation.DonationWeb.service;



import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.post.dto.AddPostRequest;
import com.donation.DonationWeb.post.service.PostService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest
public class PostServiceTest {
    @Autowired
    private PostService postService;

    @BeforeEach
    public void insert(){

    }
    @AfterEach
    public void delete(){
    }
    @Test
    public void update() throws InterruptedException {
        AddPostRequest addPostRequest = new AddPostRequest("gdsg", "sdfg", "senior");

        Post post = postService.savePost(addPostRequest, 1L);

        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    postService.updateCurrentAmount(post.getId(), 1);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        Post byId = postService.findById(post.getId());

        Assertions.assertEquals(100,byId.getCurrentAmount());

        postService.delete(post.getId(), 1L);
    }

}
