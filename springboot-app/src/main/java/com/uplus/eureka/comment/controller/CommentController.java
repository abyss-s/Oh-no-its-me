package com.uplus.eureka.comment.controller;

import com.uplus.eureka.comment.model.dto.Comment;
import com.uplus.eureka.comment.model.dto.CommentDeleteRequest;
import com.uplus.eureka.comment.model.dto.CommentRequest;
import com.uplus.eureka.comment.model.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "Comment Management API", description = "댓글 관리 API")
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(summary = "Get all comments", description = "Retrieve all comments")
    @ApiResponse(responseCode = "200", description = "모든 댓글 조회 성공")
    @GetMapping
    public ResponseEntity<?> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        if(comments.isEmpty()) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @Operation(summary = "Get comment by ID", description = "Retrieve comment information by commentID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 조회 성공"),
            @ApiResponse(responseCode = "404", description = "댓글을 찾을 수 없음")
    })
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("commentId") Integer commentId) {
        Comment comment = commentService.getCommentById(commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @Operation(summary = "POST new Comment", description = "POST new Comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "댓글 등록 성공"),
            @ApiResponse(responseCode = "404", description = "댓글 등록 오류")
    })
    @PostMapping
    public ResponseEntity<String> insertComment(@RequestBody CommentRequest comment) {
        System.out.println(comment);
        if(comment.getUserId() == null){
            return new ResponseEntity<String>("FAILED", HttpStatus.BAD_REQUEST);
        }
        commentService.insertComment(comment);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.CREATED);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<String> updateComment(
            @PathVariable("commentId") Integer commentId,
            @RequestBody CommentRequest commentRequest) {

        // 서비스 호출
        commentService.updateComment(commentId,commentRequest);

        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteCommentById(
            @PathVariable("commentId") Integer commentId,
            @RequestBody CommentDeleteRequest commentDeleteRequest) {

        commentService.deleteCommentById(commentId, commentDeleteRequest); // Call service to delete the comment
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

}
