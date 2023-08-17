package com.donation.DonationWeb.post.repository;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.post.dto.PostResponse;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    void update(UpdatePostRequest updateParam,Post post,Category category);


    void delete(Post post);

    Optional<Post> findById(Long postId);

    Optional<Post> findByIdLock(Long postId);

    Optional<Post> findByIdLeftJoin(Long postId);

    List<Post> findByMemberId(String memberId);
    List<Post> findByPage(Integer page);
    List<Post> findByCategry(Long categoryId,Integer page);

    List<Post> findAll();

}
