package com.springmvcapp.model;

import com.springmvcapp.status.PostStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PostModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  private String adminId;          // người đăng bài
  private Long relatedRoomId;    // nếu bài viết liên quan đến 1 phòng cụ thể
  private PostStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @Transient
  private MotelModel motelModel;
}
