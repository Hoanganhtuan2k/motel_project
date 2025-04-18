package com.springmvcapp.model;

import com.springmvcapp.status.PostStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
  private String relatedRoomId;    // nếu bài viết liên quan đến 1 phòng cụ thể
  private PostStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

}
