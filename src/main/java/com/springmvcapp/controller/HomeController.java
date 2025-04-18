package com.springmvcapp.controller;

import com.springmvcapp.model.PostModel;
import com.springmvcapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
//@RequiredArgsConstructor
public class HomeController {
    @Autowired
    private PostService postService;

    @GetMapping
    public String getHomePage(Model model) {
        List<PostModel> posts = postService.getAllPosts(); // Lấy dữ liệu từ API getAllPost
        model.addAttribute("posts", posts);
        return "home_page";
    }


}
