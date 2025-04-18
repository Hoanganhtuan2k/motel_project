package com.springmvcapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // Tên sinh viên
    private String email; // Email
    private String phone; // Số điện thoại
    private String password; // Mật khẩu (được mã hóa)
    private String reviews; // Liên kết danh sách đánh giá của sinh viên
    private LocalDateTime registeredAt; // Ngày đăng ký tài khoản
}
