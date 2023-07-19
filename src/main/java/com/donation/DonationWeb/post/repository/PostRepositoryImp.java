package com.donation.DonationWeb.post.repository;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.exception.PostException;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
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
    public void update(UpdatePostRequest updateParam,Category category) {
        Post findPost = findById(updateParam.getPostId()).orElseThrow(() -> new PostException("not found postId : " + updateParam.getPostId()));
        findPost.CategoryChangeAndUpdateValidate(updateParam,category);

    }
    @Override
    //게시물 불러오기
    public Optional<Post> findById(Long postId) {
        Post post = em.find(Post.class, postId);
        return Optional.ofNullable(post);
    }

    @Override
    public List<Post> findByMemberId(String memberId) {
    return    em.createQuery("select p from Post p where p.member.memberId=:memberid")
            .setParameter("memberid", memberId).getResultList();

    }

    @Override
    public List<Post> findByPage(Integer page) {
       return em.createQuery("select p from Post p order by p.createTime DESC ",Post.class).setFirstResult(page*10).setMaxResults(10).getResultList();
    }

    @Override
    public List<Post> findByCategry(Long categoryId, Integer page) {
        log.info("Page={}",page);
        return em.createQuery("select p from Post p where p.categorie.id=:category_id order by p.createTime DESC ",Post.class)
                        .setParameter("category_id",categoryId).setFirstResult(page*10).setMaxResults(10).getResultList();

    }

    @Override
    public List<Post> findAll() {
        return em.createQuery("select p from Post p",Post.class).getResultList();
    }


    @Override
    public void delete(Long postId) {

        em.remove(findById(postId).orElseThrow(() ->
                new PostException("not found postId : " + postId)));
    }
}
