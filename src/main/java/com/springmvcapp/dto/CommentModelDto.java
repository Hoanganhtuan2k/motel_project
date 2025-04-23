package com.springmvcapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.springmvcapp.model.CommentModel}
 */
@Value
@Data
@AllArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommentModelDto implements Serializable {
    String relatedRoomId;
    String userId;
    String adminId;
    String content;
    int star;
}