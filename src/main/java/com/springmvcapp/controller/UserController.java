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

    @Autowired
    private UserModelRepository userRepository;

}
