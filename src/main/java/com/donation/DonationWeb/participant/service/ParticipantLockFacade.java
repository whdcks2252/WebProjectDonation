package com.donation.DonationWeb.participant.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class ParticipantLockFacade {

    private final ParticipantService participantService;
    private final RedissonClient redissonClient;

    public void participantPut(Long volunteerPostId, Long loginId) {
        RLock lock = redissonClient.getLock(String.format("participantPut:volunteerPost:%d", volunteerPostId));
        try {
            boolean available = lock.tryLock(10, 1, TimeUnit.SECONDS);
            if (!available) {
                System.out.println("redisson getLock timeout");
                throw new IllegalArgumentException();
            }
            participantService.participantPut(volunteerPostId, loginId);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}