package com.springmvcapp.model;

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
  private Long ownerId; // Liên kết với chủ trọ (Khóa ngoại)
  private String title; // Tiêu đề bài viết
  private String content; // Nội dung bài viết
  private Long motelId; // Liên kết với phòng trọ (Khóa ngoại)
  private LocalDateTime createdAt; // Ngày tạo bài đăng
  private LocalDateTime updatedAt; // Ngày chỉnh sửa bài đăng

}
