package com.springmvcapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminModel {
    private Long id;
    private String name; // Tên Admin
    private String email; // Email đăng nhập
    private String password; // Mật khẩu (được mã hóa)
    private LocalDateTime createdAt; // Ngày tạo tài khoản
}
