package com.donation.DonationWeb.repository;

import com.donation.DonationWeb.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    //게시물 생성
    public void save(Post post) {
        em.persist(post);
    }

    //게시물 불러오기
    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p",Post.class).getResultList();
    }

}
