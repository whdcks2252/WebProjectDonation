package com.donation.DonationWeb.post.repository;

import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImp implements PostRepository {

    private final EntityManager em;

    @Override
    //게시물 생성
    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    //게시물 업데이트
    @Override
    public void update(Long postId, UpdatePostRequest updateParam) {
        Post findPost = findById(postId).orElseThrow(() -> new IllegalArgumentException("not found : " + postId));
        findPost.updateValidate(updateParam);
    }

    @Override
    public void delete(Long postId) {
        System.out.println(findById(postId).orElseThrow().getId());
        em.remove(findById(postId).orElseThrow(() ->
                new IllegalArgumentException("not found : " + postId)));
    }

    @Override
    //게시물 불러오기
    public Optional<Post> findById(Long postId) {
        Post post = em.find(Post.class, postId);
        return Optional.ofNullable(post);
    }
    @Override
    public List<Post> findAll() {
        return em.createQuery("select p from Post p",Post.class).getResultList();
    }

}
