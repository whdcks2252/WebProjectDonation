package com.donation.DonationWeb.volunteerPost.service;

import com.donation.DonationWeb.category.service.CategoryService;
import com.donation.DonationWeb.domain.*;
import com.donation.DonationWeb.exception.PostException;
import com.donation.DonationWeb.member.service.MemberService;
import com.donation.DonationWeb.volunteerPost.dto.CreateVolunteerPostRequest;
import com.donation.DonationWeb.volunteerPost.dto.UpdateVolunteerPostRequest;
import com.donation.DonationWeb.volunteerPost.dto.VolunteerPostResponse;
import com.donation.DonationWeb.volunteerPost.repository.VolunteerPostRepository;
import com.donation.DonationWeb.volunteerPost.service.VolunteerPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class VolunteerPostServiceImp implements VolunteerPostService {

    private final VolunteerPostRepository volunteerPostRepository;
    private final MemberService memberService;
    private final CategoryService categoryService;

    @Transactional
    @Override
    public VolunteerPost savePost(CreateVolunteerPostRequest request, Long id) {
        Member member = memberService.findById(id);
        Category category = categoryService.findByName(request.getCategoryName());
        return volunteerPostRepository.save(request.toEntity(category,member));
    }

    @Override
    public VolunteerPost findById(Long volunteerPostId) {
        return volunteerPostRepository.findById(volunteerPostId).orElseThrow(() -> new PostException("not found volunteerPostId: " + volunteerPostId));
    }

    @Override
    public VolunteerPost findByIdLeftJoin(Long volunteerPostId) {
        return volunteerPostRepository.findByIdLeftJoin(volunteerPostId).orElseThrow(() -> new PostException("not found volunteerPostId: " + volunteerPostId));
    }

    @Override
    public List<VolunteerPost> findByPage(Integer page) {
        return volunteerPostRepository.findByPage(page);
    }

    @Override
    public List<VolunteerPost> findByCategory(String categoryName, Integer page) {
        return volunteerPostRepository.findByCategory(categoryService.findByName(categoryName).getId(), page);
    }

    @Transactional
    @Override
    public void updatePost(UpdateVolunteerPostRequest request, Long volunteerPostId, Long loginId) {
        VolunteerPost findPost = findById(volunteerPostId);

        if (postMemberValidation(findPost, loginId)) {
                Category findCategory = categoryService.findByName(request.getCategoryName());
                volunteerPostRepository.update(request,findPost,findCategory);
        }
        else {
            throw new PostException("업데이트가 실패 하였습니다");
        }
    }

    @Override
    public void delete(Long volunteerPostId, Long loginId) {
        VolunteerPost findPost = findById(volunteerPostId);

        if(postMemberValidation(findPost,loginId) ){//멤버 아이디랑 게시물을 검증한다
            volunteerPostRepository.delete(findPost);

        }else
        {
            throw new PostException("게시물 삭제가 실패 하였습니다");
        }
    }

    @Override
    public Integer currentParticipantAmount(Long volunteerPostId) {
        return volunteerPostRepository.findParticipants(volunteerPostId).size();
    }

    @Override
    public void updateCurrentParticipantAmount(Long volunteerPostId) {
        findByIdLeftJoin(volunteerPostId).UpdateCurrentParticipantAmount(currentParticipantAmount(volunteerPostId));
    }


    private boolean postMemberValidation(VolunteerPost volunteerPost,Long loginId) {//로그인된 멤버 아이디랑 게시물을 검증한다
        Member findByLoginId = memberService.findById(loginId);

        if(findByLoginId.getId()==volunteerPost.getMember().getId() ){
            return true;
        }

        return false;
    }


}
