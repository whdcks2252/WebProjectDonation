package com.donation.DonationWeb.service;



import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.VolunteerPost;
import com.donation.DonationWeb.domain.status.ServiceAgreement;
import com.donation.DonationWeb.member.dto.AddMemberRequest;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.participant.service.ParticipantLockFacade;
import com.donation.DonationWeb.participant.service.ParticipantService;
import com.donation.DonationWeb.volunteerPost.dto.CreateVolunteerPostRequest;
import com.donation.DonationWeb.volunteerPost.service.VolunteerPostService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@SpringBootTest
public class ParticipantServiceTest {
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private ParticipantLockFacade participantLockFacade;
    @Autowired
    private VolunteerPostService volunteerPostService;
    @Autowired
    private MemberService memberService;

    @BeforeEach
    public void insert(){

    }
    @AfterEach
    public void delete(){
    }
    @Test
    void 동시에100명이봉사참가희망() throws InterruptedException {
        CreateVolunteerPostRequest createVolunteerPostRequest = new CreateVolunteerPostRequest("gdsg", "sdfg", "senior", 100);

        Member member1 = memberService.save(new AddMemberRequest("aedw722r","a","a","a"
                ,"a","a","a","a","a", ServiceAgreement.YES));
        VolunteerPost volunteerPost = volunteerPostService.savePost(createVolunteerPostRequest, member1.getId());
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    participantLockFacade.participantPut(volunteerPost.getId(), 27L+ finalI);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        VolunteerPost byId = volunteerPostService.findById(volunteerPost.getId());

        Assertions.assertEquals(100,byId.getCurrentParticipantAmount());

    }

}
