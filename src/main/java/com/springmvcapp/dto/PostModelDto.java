package com.springmvcapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springmvcapp.status.PostStatus;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * DTO for {@link com.springmvcapp.model.PostModel}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostModelDto implements Serializable {

  private String title;
  private String content;
  private String adminId;          // người đăng bài
  private String relatedRoomId;    // nếu bài viết liên quan đến 1 phòng cụ thể
  private PostStatus status;

  private MotelModelDto motel;
}