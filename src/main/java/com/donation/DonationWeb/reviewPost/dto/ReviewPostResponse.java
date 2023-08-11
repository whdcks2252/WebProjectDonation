package com.donation.DonationWeb.reviewPost.dto;

import com.donation.DonationWeb.domain.ReviewPost;
import com.donation.DonationWeb.volunteerPost.dto.VolunteerPostResponse;
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
public class ReviewPostResponse {
    private Long VolunteerPostId;
    private String title;
    private String content;
    private String categoryName;
    private Long categoryNum;
    private Long postId;
    private String memberId;
    private String memberNickname;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<CommentDto> comments;

    public static ReviewPostResponse createInstance(ReviewPost reviewPost) {
        return new ReviewPostResponse(reviewPost.getId(), reviewPost.getTitle(), reviewPost.getContent(), reviewPost.getCategorie().getCategoryName(),reviewPost.getCategorie().getId(), reviewPost.getPost().getId(),
                reviewPost.getPost().getMember().getMemberId(), reviewPost.getPost().getMember().getMemberNickname(), reviewPost.getCreateTime(), reviewPost.getUpdateTime(),
                reviewPost.getCommemts().stream().
                        map(comment -> ReviewPostResponse.CommentDto.builder().content(comment.getContent()).commentId(comment.getId())
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
