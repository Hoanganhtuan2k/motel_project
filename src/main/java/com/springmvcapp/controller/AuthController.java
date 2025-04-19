package com.springmvcapp.controller;


import com.springmvcapp.model.UserModel;
import com.springmvcapp.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new UserModel());
        return "registration_page";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("user") UserModel user, Model model) {
        String result = authService.register(user);

        if (result.equals("success")) {
            model.addAttribute("success", "Đăng ký thành công! Bạn có thể đăng nhập.");
            model.addAttribute("user", new UserModel()); // reset form
        } else {
            model.addAttribute("error", result);
        }

        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login_page";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("email") String email,
                               @RequestParam("password") String password,
                               HttpSession session,
                               Model model) {
        UserModel user = authService.login(email, password);
        if (user != null) {
            session.setAttribute("loggedInUser", user); // lưu user vào session
            return "redirect:/"; // điều hướng tới trang chính (tuỳ bạn)
        } else {
            model.addAttribute("error", "Email hoặc mật khẩu không đúng.");
            return "login_page";
        }
    }



}
