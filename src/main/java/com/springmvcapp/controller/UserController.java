package com.springmvcapp.controller;

import com.springmvcapp.dto.UserModelDto;
import com.springmvcapp.model.UserModel;
import com.springmvcapp.service.UserService;
import com.springmvcapp.service.repo.UserModelRepository;
import com.springmvcapp.status.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Autowired
    private UserModelRepository userRepository;

    @GetMapping("/role/{role}")
    public String viewUsersByRole(@PathVariable String role, Model model) {
        if ("USER".equalsIgnoreCase(role)) {
            List<UserModel> users = userService.getUsersByRole(UserRole.USER);
            System.out.println("Danh sách người thuê: " + users); // Log để kiểm tra
            model.addAttribute("users", users);
            return "contract_create";
        } else {
            model.addAttribute("error", "Vai trò không hợp lệ!");
            return "error";
        }
    }
}
