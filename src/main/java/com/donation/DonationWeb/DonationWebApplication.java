package com.donation.DonationWeb;

import com.donation.DonationWeb.category.service.CategoryServiceImp;
import com.donation.DonationWeb.post.service.PostServiceImp;
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
	public TestDataInit testDataInit(PostServiceImp itemRepository, EntityManager em, CategoryServiceImp categoryServiceImp) {
		return new TestDataInit(itemRepository,categoryServiceImp,em);
	}
}
