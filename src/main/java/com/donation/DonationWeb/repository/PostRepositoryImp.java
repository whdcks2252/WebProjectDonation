package com.donation.DonationWeb.repository;

import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.dto.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImp implements PostRepository{

    private final EntityManager em;

    @Override
    //게시물 생성
    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    @Override
    public void update(Long itemId, UpdatePostRequest updateParam) {
        Post findPost = findById(itemId).orElseThrow();
        findPost.setTitle(updateParam.getTitle());
        findPost.setContent(updateParam.getContent());

    }

    @Override
    //게시물 불러오기
    public Optional<Post> findById(Long id) {
        Post post = em.find(Post.class, id);
        return Optional.ofNullable(post);
    }
    @Override
    public List<Post> findAll() {
        return em.createQuery("select p from Post p",Post.class).getResultList();
    }

}
