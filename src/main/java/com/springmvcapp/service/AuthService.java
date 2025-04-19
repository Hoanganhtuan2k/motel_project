package com.springmvcapp.service;


import com.springmvcapp.model.UserModel;
import com.springmvcapp.service.repo.UserModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService  {

    @Autowired
    private UserModelRepository userModelRepository;

    private final PasswordEncoder passwordEncoder;

    public String register(UserModel user) {
        if (userModelRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email đã được sử dụng";
        }

        if (userModelRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Tên đăng nhập đã tồn tại";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Mật khẩu xác nhận không khớp";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userModelRepository.save(user);

        return "success";
    }

    public UserModel login(String email, String password) {
        return userModelRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(null);
    }



}
