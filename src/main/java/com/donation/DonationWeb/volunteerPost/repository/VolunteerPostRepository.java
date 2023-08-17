package com.donation.DonationWeb.volunteerPost.repository;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.VolunteerPost;
import com.donation.DonationWeb.volunteerPost.dto.UpdateVolunteerPostRequest;

import java.util.List;
import java.util.Optional;

public interface VolunteerPostRepository {

    VolunteerPost save(VolunteerPost volunteerPost);

    Optional<VolunteerPost> findById(Long volunteerPostId);

    Optional<VolunteerPost> findByIdLeftJoin(Long volunteerPostId);

    List<VolunteerPost> findByPage(Integer page);

    List<VolunteerPost> findByCategory(Long categoryId, Integer page);

    void update(UpdateVolunteerPostRequest request, VolunteerPost volunteerPost, Category category);

    void delete(VolunteerPost volunteerPost);

    List<VolunteerPost> findParticipants(Long volunteerPostId);
}
