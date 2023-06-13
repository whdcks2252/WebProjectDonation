package com.donation.DonationWeb;

import com.donation.DonationWeb.domain.Categorie;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.PostStatus;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import com.donation.DonationWeb.post.dto.addPostRequest;
import com.donation.DonationWeb.post.service.PostServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final PostServiceImp postRepository;
    private final EntityManager em;
    /**
     * 확인용 초기 데이터 추가
     */
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initData() {
        log.info("test data init");
        em.persist(new Categorie("동물"));
      Post post=  postRepository.savePost(new addPostRequest("title1","gdgdg"));
         postRepository.savePost(new addPostRequest("title2","gdgdggd"));
        postRepository.updatePost(post.getId(),UpdatePostRequest.builder().title("test").content("test").postStatus(PostStatus.EXPIRATION).build());
        postRepository.updatePost(post.getId(),UpdatePostRequest.builder().title("gd").postStatus(PostStatus.EXPIRATION).build());
       // postRepository.delete(post.getId());
    }

}
