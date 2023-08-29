package com.donation.DonationWeb;

import com.donation.DonationWeb.adminUser.dto.CreateAdminUserRequest;
import com.donation.DonationWeb.adminUser.service.AdminUserService;
import com.donation.DonationWeb.category.dto.AddCategoryRequest;
import com.donation.DonationWeb.category.dto.UpdateCategoryRequest;
import com.donation.DonationWeb.category.service.CategoryServiceImp;
import com.donation.DonationWeb.comment.dto.AddCommentRequest;
import com.donation.DonationWeb.comment.service.CommentService;
import com.donation.DonationWeb.domain.*;
import com.donation.DonationWeb.domain.status.ServiceAgreement;
import com.donation.DonationWeb.member.dto.AddMemberRequest;
import com.donation.DonationWeb.member.dto.MemberUpdateDto;
import com.donation.DonationWeb.member.service.MemberServiceImp;
import com.donation.DonationWeb.participant.service.ParticipantService;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import com.donation.DonationWeb.post.dto.AddPostRequest;
import com.donation.DonationWeb.post.service.PostServiceImp;
import com.donation.DonationWeb.reviewPost.dto.CreateReviewPostRequest;
import com.donation.DonationWeb.reviewPost.service.ReviewPostService;
import com.donation.DonationWeb.volunteerPost.dto.CreateVolunteerPostRequest;
import com.donation.DonationWeb.volunteerPost.service.VolunteerPostService;
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
    private final MemberServiceImp memberServiceImp;
    private final VolunteerPostService volunteerPostService;
    private final ParticipantService participantService;
    private final CommentService commentService;
    private final ReviewPostService reviewPostService;
    private final AdminUserService adminUserService;
    /**
     * 확인용 초기 데이터 추가
     */
    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initData() {
        log.info("test data init");

        Member save = memberServiceImp.save(new AddMemberRequest
                ("whdcks1", "ㅎㅇㅎㅇ", "123123", "원종찬", "01044444444", "sadasdasdas",
                        "seoul","boroad","123", ServiceAgreement.YES));
        Member save2 = memberServiceImp.save(new AddMemberRequest
                ("whdcks2", "ㅎㅇㅎㅇ", "123123", "원종찬",
                        "01044444444", "sadasdasdas","seoul","boroad","123",ServiceAgreement.YES));
        Member save3 = memberServiceImp.save(new AddMemberRequest
                ("whdcks3", "ㅎㅇㅎㅇ", "123123", "원종찬", "01044444444",
                        "sadasdasdas","seoul","boroad","123",ServiceAgreement.YES));
        memberServiceImp.findById(save.getId());
        memberServiceImp.update(save.getId(),new MemberUpdateDto("닉네임변경","123123","asgasgd","change","change","change"));
        memberServiceImp.delete(save2.getId());
        List<Member> all = memberServiceImp.findAll();
        for (Member member : all) {
            System.out.println(member.getMemberName());

        }

        Category category=  categoryServiceImp.saveCategory(new AddCategoryRequest("동물"));
        Category category1 = categoryServiceImp.saveCategory(new AddCategoryRequest("senior"));
        categoryServiceImp.updateCategory(category.getId(),UpdateCategoryRequest.builder().name("동물 수정").build());

      Post post=  postRepository.savePost(new AddPostRequest("title1","gdgdg", category.getCategoryName()), save.getId());
        postRepository.savePost(new AddPostRequest("title2","gdgdggd", category1.getCategoryName()),save3.getId());
        postRepository.savePost(new AddPostRequest("title2","gdgdggd", category1.getCategoryName()),save3.getId());
        postRepository.savePost(new AddPostRequest("title2","gdgdggd", category1.getCategoryName()),save3.getId());
        postRepository.savePost(new AddPostRequest("title2","gdgdggd", category1.getCategoryName()),save3.getId());
        postRepository.savePost(new AddPostRequest("title2","gdgdggd", category1.getCategoryName()),save3.getId());
        postRepository.savePost(new AddPostRequest("title2","gdgdggd", category1.getCategoryName()),save3.getId());
        postRepository.savePost(new AddPostRequest("title2","gdgdggd", category1.getCategoryName()),save3.getId());
        postRepository.savePost(new AddPostRequest("title2","gdgdggd", category1.getCategoryName()),save3.getId());
        postRepository.savePost(new AddPostRequest("title2","gdgdggd", category.getCategoryName()),save3.getId());
        postRepository.savePost(new AddPostRequest("title2","gdgdggd", category.getCategoryName()),save3.getId());

        postRepository.savePost(new AddPostRequest("title2","gdgdggd", category.getCategoryName()),save3.getId());
        postRepository.savePost(new AddPostRequest("title2","gdgdggd", category.getCategoryName()),save3.getId());
        postRepository.savePost(new AddPostRequest("title2","gdgdggd", category.getCategoryName()),save3.getId());


        VolunteerPost volunteerPost1 = volunteerPostService.savePost(new CreateVolunteerPostRequest("title1","gdgdg", category.getCategoryName(), 2), save.getId());
        VolunteerPost volunteerPost2 = volunteerPostService.savePost(new CreateVolunteerPostRequest("title2","gdgdggd", category1.getCategoryName(),2),save3.getId());
        VolunteerPost volunteerPost3 = volunteerPostService.savePost(new CreateVolunteerPostRequest("title2","gdgdggd", category.getCategoryName(),2),save3.getId());
        participantService.participantPut(volunteerPost1.getId(), save.getId());
        participantService.participantPut(volunteerPost1.getId(), save3.getId());
        volunteerPostService.updateCurrentParticipantAmount(volunteerPost1.getId());


        ReviewPost reviewPost = reviewPostService.savePost(new CreateReviewPostRequest("title", "deedede", category.getCategoryName(), post.getId()), save.getId());
        AdminUser adminUser = adminUserService.save(new CreateAdminUserRequest("chooh1010", "1234", "admin"));

        for (int i =0;i<100;i++) {
            memberServiceImp.save(new AddMemberRequest("a"+String.valueOf(i),"a"+String.valueOf(i),"a"+String.valueOf(i),"a"+String.valueOf(i),"a"+String.valueOf(i),"a"+String.valueOf(i),"a"+String.valueOf(i),"a"+String.valueOf(i),"a"+String.valueOf(i), ServiceAgreement.YES));
        }








    }

}
