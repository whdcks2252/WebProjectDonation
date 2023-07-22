package com.donation.DonationWeb.member.dto;

import com.donation.DonationWeb.domain.InterestPost;
import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class MemberInterestPostResponse {

    private Long id;

    private List<interestPostsDto> interestPosts;

    public static MemberInterestPostResponse createInstance(Member member) {
        return new MemberInterestPostResponse(member.getId(),
                member.getInterestPosts().stream()
                        .map(interPost -> interestPostsDto
                                .builder().interPostId(interPost.getId()).PostId(interPost.getPost().getId()).title(interPost.getPost().getTitle())
                                .postStatus(interPost.getPost().getPostStatus()).updateTime(interPost.getUpdateTime())
                                .categoryName(interPost.getPost().getCategorie().getCategoryName()).categoryNum(interPost.getPost().getCategorie().getId())
                                .build()).collect(Collectors.toList())
        );

    }

    @Getter
    static class interestPostsDto{
        private Long interPostId;
        private Long PostId;
        private String title;
        private String categoryName;
        private Long categoryNum;
        private PostStatus postStatus;
        private LocalDateTime updateTime;

        @Builder
        public interestPostsDto(Long interPostId,Long PostId,String title,PostStatus postStatus,LocalDateTime updateTime, String categoryName, Long categoryNum) {
            this.interPostId = interPostId;
            this.PostId = PostId;
            this.title = title;
            this.postStatus= postStatus;
            this.updateTime = updateTime;
            this.categoryName = categoryName;
            this.categoryNum = categoryNum;
        }
    }



}
