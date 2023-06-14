package com.donation.DonationWeb;

import com.donation.DonationWeb.category.dto.AddCategoryRequest;
import com.donation.DonationWeb.category.dto.UpdateCategoryRequest;
import com.donation.DonationWeb.category.service.CategoryServiceImp;
import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.PostStatus;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import com.donation.DonationWeb.post.dto.AddPostRequest;
import com.donation.DonationWeb.post.service.PostServiceImp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final PostServiceImp postRepository;
    private final CategoryServiceImp categoryServiceImp;
    private final EntityManager em;
    /**
     * 확인용 초기 데이터 추가
     */
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initData() {
        log.info("test data init");

        Category category=  categoryServiceImp.saveCategory(new AddCategoryRequest("동물"));
        categoryServiceImp.saveCategory(new AddCategoryRequest("노인"));
        categoryServiceImp.updateCategory(category.getId(),UpdateCategoryRequest.builder().name("동물 수정").build());

      Post post=  postRepository.savePost(new AddPostRequest("title1","gdgdg"),category);
         postRepository.savePost(new AddPostRequest("title2","gdgdggd"),category);
        postRepository.updatePost(post.getId(),UpdatePostRequest.builder().title("test").content("test").postStatus(PostStatus.EXPIRATION).build());
        postRepository.updatePost(post.getId(),UpdatePostRequest.builder().title("gd").postStatus(PostStatus.EXPIRATION).build());

        // postRepository.delete(post.getId());
    }

}
