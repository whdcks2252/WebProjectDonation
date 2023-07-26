package com.donation.DonationWeb.member.dto;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.PostStatus;
import com.donation.DonationWeb.post.dto.PostResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@AllArgsConstructor
@Getter
public class MemberPostResponse {

    private Long id;

    private List<PostDto> posts;

    public static MemberPostResponse createInstance(Member member) {
        return new MemberPostResponse(member.getId(),
                member.getPosts().stream()
                        .map(post -> PostDto
                                .builder().PostId(post.getId()).title(post.getTitle())
                                .postStatus(post.getPostStatus()).updateTime(post.getUpdateTime())
                                .categoryName(post.getCategorie().getCategoryName()).categoryNum(post.getCategorie().getId())
                                .build()).collect(Collectors.toList())
        );

    }

    @Getter
    static class PostDto{
        private Long PostId;
        private String title;
        private String categoryName;
        private Long categoryNum;
        private PostStatus postStatus;
        private LocalDateTime updateTime;

        @Builder
        public PostDto(Long PostId,String title,PostStatus postStatus,LocalDateTime updateTime, String categoryName, Long categoryNum) {
            this.PostId = PostId;
            this.title = title;
            this.postStatus= postStatus;
            this.updateTime = updateTime;
            this.categoryName = categoryName;
            this.categoryNum = categoryNum;
        }
    }



}
