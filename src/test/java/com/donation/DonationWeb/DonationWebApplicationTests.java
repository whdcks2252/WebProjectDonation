package com.donation.DonationWeb;

import com.donation.DonationWeb.domain.Post;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootTest

class DonationWebApplicationTests {




@Test
	void contextLoads() {
	//엔티티 매니저 팩토리 생성

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
	//엔티티 매니저 생성

	EntityManager em = emf.createEntityManager();
	//트랜잭션 획득

	EntityTransaction tx = em.getTransaction();
	tx.begin();
	try {

		List<Post> resultList = em.createQuery("select p from Post p where p.categorie.id=:category_id order by p.id ASC", Post.class).
				setParameter("category_id", "senior").setFirstResult(0 * 10).setMaxResults(10).getResultList();

		for (Post post : resultList) {
			System.out.println(post.getCategorie());

		}


	} catch (Exception e) {
		tx.rollback();
	}
	finally {
		em.close();
	}


	emf.close();
	}

}
