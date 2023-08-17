package com.donation.DonationWeb.volunteerPost.dto;

import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.PostStatus;
import com.donation.DonationWeb.domain.VolunteerPost;
import com.donation.DonationWeb.post.dto.PostResponse;
import com.donation.DonationWeb.volunteerPost.repository.VolunteerPostRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerPostResponse {
    private Long VolunteerPostId;
    private String title;
    private String content;
    private String categoryName;
    private Long categoryNum;
    private PostStatus postStatus = PostStatus.PROCESS;// 생성시 진행중
    private String memberId;
    private String memberNickname;
    private Integer needAmount;
    private Integer currentParticipantAmount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<CommentDto> comments;


    public static VolunteerPostResponse createInstance(VolunteerPost volunteerPost) {
        return new VolunteerPostResponse(volunteerPost.getId(), volunteerPost.getTitle(), volunteerPost.getContent(), volunteerPost.getCategorie().getCategoryName(), volunteerPost.getCategorie().getId(), volunteerPost.getPostStatus(),
                volunteerPost.getMember().getMemberId(), volunteerPost.getMember().getMemberNickname(), volunteerPost.getNeedAmount(), volunteerPost.getCurrentParticipantAmount(), volunteerPost.getCreateTime(), volunteerPost.getUpdateTime(),
                volunteerPost.getCommemts().stream().
                        map(comment -> CommentDto.builder().content(comment.getContent()).commentId(comment.getId())
                                .memberId(comment.getMember().getMemberId())
                                .updateTime(comment.getUpdateTime()).build()).collect(Collectors.toList())
        );
    }

    @Getter
    static class CommentDto {
        private String content;
        private Long commentId;
        private String memberId;
        private LocalDateTime updateTime;

        @Builder
        public CommentDto(String content, Long commentId, String memberId, LocalDateTime updateTime) {
            this.content = content;
            this.commentId = commentId;
            this.memberId = memberId;
            this.updateTime = updateTime;
        }
    }
}
