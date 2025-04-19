package com.springmvcapp.controller;

import com.springmvcapp.dto.UserModelDto;
import com.springmvcapp.model.UserModel;
import com.springmvcapp.service.UserService;
import com.springmvcapp.service.repo.UserModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login_page";
    }

    @Autowired
    private UserModelRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "registration_page";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") UserModel user,
        Model model) {

        // Kiểm tra xác nhận mật khẩu
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("error", "Mật khẩu xác nhận không trùng khớp");
            return "registration_page";
        }

        // Kiểm tra tên đăng nhập đã tồn tại
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại");
            return "registration_page";
        }

        // Mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Lưu người dùng
        userRepository.save(user);

        model.addAttribute("success", "Đăng ký thành công! Bạn có thể đăng nhập.");
        model.addAttribute("user", new UserModel()); // Reset form
        return "registration_page";
    }
}
