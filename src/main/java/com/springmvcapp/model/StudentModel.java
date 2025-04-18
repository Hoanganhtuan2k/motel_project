package com.springmvcapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel {
    private Long id;
    private String name; // Tên sinh viên
    private String email; // Email
    private String phone; // Số điện thoại
    private String password; // Mật khẩu (được mã hóa)
    private List<Long> reviews; // Liên kết danh sách đánh giá của sinh viên
    private LocalDateTime registeredAt; // Ngày đăng ký tài khoản
}
