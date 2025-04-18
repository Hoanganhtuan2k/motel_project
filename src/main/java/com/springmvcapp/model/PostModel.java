package com.springmvcapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostModel {
    private Long id;
    private Long ownerId; // Liên kết với chủ trọ (Khóa ngoại)
    private String title; // Tiêu đề bài viết
    private String content; // Nội dung bài viết
    private Long motelId; // Liên kết với phòng trọ (Khóa ngoại)
    private LocalDateTime createdAt; // Ngày tạo bài đăng
    private LocalDateTime updatedAt; // Ngày chỉnh sửa bài đăng

    public PostModel(long l, long l1, String s, String s1, double v, String s2, String number, LocalDateTime localDateTime, LocalDateTime localDateTime1) {
    }
}
