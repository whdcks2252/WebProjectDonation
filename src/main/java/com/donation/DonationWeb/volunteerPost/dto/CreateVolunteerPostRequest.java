package com.donation.DonationWeb.volunteerPost.dto;

import com.donation.DonationWeb.domain.Category;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.VolunteerPost;
import com.donation.DonationWeb.domain.status.PostStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class CreateVolunteerPostRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String categoryName;
    private PostStatus postStatus=PostStatus.PROCESS;// 생성시 진행중
    @NotNull
    private Integer needAmount;
    private Integer currentParticipantAmount = 0;

    public CreateVolunteerPostRequest(String title, String content, String categoryName, Integer needAmount) {
        this.title = title;
        this.content = content;
        this.categoryName = categoryName;
        this.needAmount = needAmount;
    }

    public VolunteerPost toEntity(Category categorie, Member member) {
        return VolunteerPost.builder()
                .title(this.title).content(this.content)
                .postStatus(this.postStatus).categorie(categorie)
                .member(member).needAmount(this.needAmount).currentParticipantAmount(currentParticipantAmount)
                .build();
    }
}
