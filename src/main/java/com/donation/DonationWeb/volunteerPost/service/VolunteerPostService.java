package com.donation.DonationWeb.volunteerPost.service;

import com.donation.DonationWeb.domain.VolunteerPost;
import com.donation.DonationWeb.volunteerPost.dto.CreateVolunteerPostRequest;
import com.donation.DonationWeb.volunteerPost.dto.UpdateVolunteerPostRequest;

import java.util.List;

public interface VolunteerPostService {
    public VolunteerPost savePost(CreateVolunteerPostRequest request, Long id);

    public VolunteerPost findById(Long volunteerPostId);

    public VolunteerPost findByIdLeftJoin(Long volunteerPostId);

    public List<VolunteerPost> findByPage(Integer page);

    public List<VolunteerPost> findByCategory(String categoryName, Integer page);

    public void updatePost(UpdateVolunteerPostRequest request, Long volunteerPostId, Long loginId);

    public void delete(Long volunteerPostId, Long loginId);

    public Integer currentParticipantAmount(Long volunteerPostId);

    public void updateCurrentParticipantAmount(Long volunteerPostId);

}
