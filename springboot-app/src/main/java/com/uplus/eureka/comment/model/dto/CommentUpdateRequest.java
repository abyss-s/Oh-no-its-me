package com.uplus.eureka.comment.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommentUpdateRequest implements Serializable {

    @JsonProperty("comment_id")
    @Schema(description = "댓글 ID", example = "1")
    private Integer commentId;

    @JsonProperty("user_id")
    @Schema(description = "댓글 작성자 ID", example = "1")
    private Integer userId;

    @JsonProperty("comment_text")
    @Schema(description = "댓글 내용", example = "좋은 투표 결과입니다.")
    private String commentText;
}
