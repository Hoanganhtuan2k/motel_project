package com.springmvcapp.model;

import com.springmvcapp.status.UserRole;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @Transient // Không lưu xuống DB
    private String confirmPassword;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
