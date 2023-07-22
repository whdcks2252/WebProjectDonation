package com.donation.DonationWeb.comment.repository;

import com.donation.DonationWeb.comment.dto.UpdateCommentRequest;
import com.donation.DonationWeb.domain.Comment;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.exception.CommentException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CommentRepositoryImp implements CommentRepository {

    private final EntityManager em;
    @Override
    public Comment save(Comment comment) {
        em.persist(comment);
        return comment;
    }

    @Override
    public void delete(Comment comment) {
        em.remove(comment);

    }

    @Override
    public void update(Comment comment,UpdateCommentRequest updateCommentRequest) {

            comment.updateComment(updateCommentRequest.getContent());
    }



    @Override
    public Optional<Comment> findById(Long commentId) {
        return Optional.ofNullable(em.find(Comment.class, commentId));

    }
    @Override
    public Optional<Comment> findCommentWithPost(Long commentId) {
        return em.createQuery("select c from Comment c" +
                        " join fetch c.post p" +
                        " where c.id=:commentId",Comment.class)
                .setParameter("commentId", commentId).getResultList().stream().findAny();

    }
}
