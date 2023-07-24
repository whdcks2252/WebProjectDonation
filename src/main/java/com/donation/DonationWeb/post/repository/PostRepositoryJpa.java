package com.donation.DonationWeb.post.repository;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PostRepositoryJpa implements PostRepository {

    private final EntityManager em;

    @Override
    //게시물 생성
    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    //게시물 업데이트
    @Override
    public void update(UpdatePostRequest updateParam,Post post,Category category) {
        post.CategoryChangeAndUpdateValidate(updateParam,category);

    }

    @Override
    //게시물 불러오기
    public Optional<Post> findById(Long postId) {
        Post post = em.find(Post.class, postId);
        return Optional.ofNullable(post);

    }


    @Override
    //null댓글 때문에
    public Optional<Post> findByIdLeftJoin(Long postId) {
        return em.createQuery("select p from Post p" +
                        " join fetch p.member m" +
                        " join fetch p.categorie c" +
                        " left join fetch p.commemts co" +
                        " where p.id=:postId ",Post.class) //레프트 조인해도 postId 값을 주니까 괜찮음
                .setParameter("postId", postId).getResultList().stream().findAny();

    }

    @Override
    public List<Post> findByMemberId(String memberId) { //성능 최적화 fetch조인
    return    em.createQuery("select p from Post p" +
                    " join fetch p.member m" +
                    " join fetch p.categorie c" +
                    " where p.member.memberId=:memberid",Post.class)
            .setParameter("memberid", memberId).getResultList();

    }


    @Override
    public List<Post> findByPage(Integer page) {
        if(page<=0) {
            page=page+1;
        }
            return em.createQuery("select p from Post p" +
                            " join fetch p.member m" +
                            " join fetch p.categorie c" +
                            " order by p.createTime DESC ",
                    Post.class).setFirstResult((page - 1) * 10)
                    .setMaxResults(10)
                    .getResultList();


        }

    @Override
    public List<Post> findByCategry(Long categoryId, Integer page) {
        if(page<=0) {
            page=page+1;
        }
        log.info("Page={}",page);
        return em.createQuery("select p from Post p" +
                        " join fetch p.member m" +
                        " join fetch p.categorie c" +
                        " where p.categorie.id=:category_id" +
                        " order by p.createTime DESC "
                        ,Post.class)
                        .setParameter("category_id",categoryId)
                        .setFirstResult((page-1)*10)
                        .setMaxResults(10).getResultList();

    }

    @Override
    public List<Post> findAll() {
        return em.createQuery("select p from Post p join" +
                        " fetch p.member m" +
                        " join fetch p.categorie c ",Post.class)
                        .getResultList();
    }


    @Override
    public void delete(Post post) {

        em.remove(post);
    }
}
