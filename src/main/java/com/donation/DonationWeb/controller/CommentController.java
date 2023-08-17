package com.donation.DonationWeb.controller;

import com.donation.DonationWeb.argumentresolver.Login;
import com.donation.DonationWeb.comment.dto.*;
import com.donation.DonationWeb.comment.service.CommentService;
import com.donation.DonationWeb.domain.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post/{postId}/comment")
@Slf4j
public class CommentController {

    private final CommentService commentService;
    @PostMapping
    public Object createComment(@PathVariable Long postId,@RequestBody @Validated AddCommentRequest addCommentRequest, @Login Long loginId) {

        Comment comment = commentService.save(addCommentRequest, loginId,postId);
        return new ResponseEntity<>(CommentResponse.createInstance(comment), HttpStatus.CREATED);

    }

    @PatchMapping("/{commentId}")
    public Object updateComment(@PathVariable Long commentId, @RequestBody UpdateCommentRequest updateCommentRequest, @Login Long loginId) {

                commentService.update(updateCommentRequest,commentId,loginId);
        return UpdateCommentResponse.createInstance(commentService.findById(commentId));

    }
    @DeleteMapping({"/{commentId}"})
    public Object deleteComment(@PathVariable Long commentId, @Login Long loginId) {

        commentService.delete(commentId,loginId);

        return   new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }

}
