package com.donation.DonationWeb.post.service;

import com.donation.DonationWeb.category.service.CategoryService;
import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.exception.PostException;
import com.donation.DonationWeb.exception.UserException;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.post.dto.DeletePostRequest;
import com.donation.DonationWeb.post.dto.PostResponse;
import com.donation.DonationWeb.post.dto.UpdatePostRequest;
import com.donation.DonationWeb.post.dto.AddPostRequest;
import com.donation.DonationWeb.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;
    private final MemberService memberService;
    private final CategoryService categoryService;

    @Transactional
    public Post savePost(AddPostRequest addPostRequest,Long id){
        Member member = memberService.findById(id);
        Category category = categoryService.findByName(addPostRequest.getCategoryName());
        return postRepository.save(addPostRequest.toEntity(category,member));
    }

    public List<Post> findAll() {return postRepository.findAll();}

    public Post findById(Long postId) {return postRepository.findById(postId).orElseThrow(() -> new PostException("not found postId: " + postId));}



    public List<Post> findByPage(Integer page){
        return postRepository.findByPage(page);
    }

    @Override
    public List<Post> findByCategry(String categoryName, Integer page) {
        log.info("ca=","1");

        return postRepository.findByCategry(categoryService.findByName(categoryName).getId(), page);

            }

    @Override
    @Transactional
    /**
     * 영속성 컨텍스트가 자동 변경
     */
    public void updatePost(UpdatePostRequest upPost, Long loginId) {
        if (postMemberValidation(upPost.getPostId(), loginId)) {
            if(categoryChange(upPost.getCategoryNum())){
                Category findCategory = categoryService.findById(upPost.getCategoryNum());
                postRepository.updateCategoryExists(upPost,findCategory);
            }
            postRepository.update(upPost);
        }
        else {
            throw new PostException("업데이트가 실패 하였습니다");
        }
    }



    @Transactional
    @Override
    public void delete(DeletePostRequest deletePostRequest,Long loginId) {

        if(postMemberValidation(deletePostRequest.getPostId(),loginId) ){//멤버 아이디랑 게시물을 검증한다

            postRepository.delete(deletePostRequest.getPostId());

        }else
        {
          throw   new PostException("게시물 삭제가 실패 햐엿습니다");
        }

    }

    private boolean postMemberValidation(Long postId,Long loginId) {//로그인된 멤버 아이디랑 게시물을 검증한다
        Post postById = findById(postId);
        Member findByLoginId = memberService.findById(loginId);

        if(findByLoginId.getId()==postById.getMember().getId() ){
            return true;
        }

        return false;
    }
    private boolean categoryChange(Long categoryId) {//카테고리 변경
        if (categoryId!=null){
            return true;
        }

        return false;
    }


}
