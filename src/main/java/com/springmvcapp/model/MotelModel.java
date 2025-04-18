package com.springmvcapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotelModel {
    private Long id;
    private String title; // Tiêu đề
    private String description; // Mô tả chi tiết
    private Double price; // Giá thuê
    private Double area; // Diện tích
    private String address; // Địa chỉ cụ thể
    private Long locationId; // Liên kết với Location (Khóa ngoại)
    private Long ownerId; // Liên kết với chủ trọ (Khóa ngoại)
    private List<String> images; // Danh sách hình ảnh
    private LocalDateTime createdAt; // Ngày đăng bài

    // Thông tin trạng thái thuê
    private Boolean isRented; // Đang được thuê (true/false)
    private LocalDateTime rentedFrom; // Ngày bắt đầu thuê (nếu có)
    private LocalDateTime rentedUntil; // Ngày kết thúc thuê (nếu có)
}
