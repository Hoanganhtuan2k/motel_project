package com.springmvcapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link com.springmvcapp.model.CommentModel}
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentModelDto implements Serializable {
    private String relatedRoomId;
    private String userId;
    private String adminId;
    private String content;
    private int star;


    // Constructor không tham số (mặc định)
    public CommentModelDto() {
    }

    // Constructor có tham số để khởi tạo nhanh
    public CommentModelDto(String relatedRoomId, String userId, String adminId, String content, int star) {
        this.relatedRoomId = relatedRoomId;
        this.userId = userId;
        this.adminId = adminId;
        this.content = content;
        this.star = star;
    }


}