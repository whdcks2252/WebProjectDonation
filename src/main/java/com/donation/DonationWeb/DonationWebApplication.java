package com.donation.DonationWeb;

import com.donation.DonationWeb.adminUser.service.AdminUserServiceImp;
import com.donation.DonationWeb.category.service.CategoryServiceImp;
import com.donation.DonationWeb.comment.service.CommentService;
import com.donation.DonationWeb.comment.service.CommentServiceImp;
import com.donation.DonationWeb.member.service.MemberServiceImp;
import com.donation.DonationWeb.participant.service.ParticipantService;
import com.donation.DonationWeb.participant.service.ParticipantServiceImp;
import com.donation.DonationWeb.post.service.PostServiceImp;
import com.donation.DonationWeb.reviewPost.service.ReviewPostServiceImp;
import com.donation.DonationWeb.volunteerPost.service.VolunteerPostServiceImp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;

@EnableJpaAuditing
@SpringBootApplication
public class DonationWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonationWebApplication.class, args);
		}
	@Bean
	@Profile("local")
	public TestDataInit testDataInit(PostServiceImp itemRepository, EntityManager em, CategoryServiceImp categoryServiceImp, MemberServiceImp memberServiceImp, VolunteerPostServiceImp volunteerPostServiceImp, ParticipantServiceImp participantServiceImp, CommentServiceImp commentServiceImp, ReviewPostServiceImp reviewPostServiceImp, AdminUserServiceImp adminUserServiceImp) {
		return new TestDataInit(itemRepository,categoryServiceImp,em,memberServiceImp,volunteerPostServiceImp, participantServiceImp, commentServiceImp, reviewPostServiceImp, adminUserServiceImp);
	}
}
