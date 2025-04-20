  package com.springmvcapp.dto;

  import com.springmvcapp.status.PostStatus;
  import java.io.Serializable;
  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;

  /**
   * DTO for {@link com.springmvcapp.model.PostModel}
   */
  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public class PostModelDto implements Serializable {
    private Long id;
    private String title;
    private String content;
    private String adminId;        
    private String relatedRoomId;
    private PostStatus status;

    private MotelModelDto motel;
  }