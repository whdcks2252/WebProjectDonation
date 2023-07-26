package com.donation.DonationWeb.post.dto;

import com.donation.DonationWeb.domain.Member;
import com.donation.DonationWeb.domain.Post;
import com.donation.DonationWeb.domain.PostStatus;
import com.donation.DonationWeb.domain.ServiceAgreement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class PostResponse {
    private Long PostId;
    private String title;
    private String content;
    private String categoryName;
    private Long categoryNum;
    private PostStatus postStatus=PostStatus.PROCESS;// 생성시 진행중
    private String memberId;
    private String memberNickname;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private List<CommentDto> comments;



    public static PostResponse createInstance(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getCategorie().getCategoryName(), post.getCategorie().getId(), post.getPostStatus(),
                post.getMember().getMemberId(), post.getMember().getMemberNickname(), post.getCreateTime(), post.getUpdateTime(),
                post.getCommemts().stream().
                        map(comment -> CommentDto.builder().content(comment.getContent()).commentId(comment.getId())
                                .memberId(comment.getMember().getMemberId())
                                .updateTime(comment.getUpdateTime()).build()).collect(Collectors.toList())
        );
    }

    @Getter
    static class CommentDto{
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
