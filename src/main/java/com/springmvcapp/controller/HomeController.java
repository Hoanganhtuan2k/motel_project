package com.springmvcapp.controller;

import com.springmvcapp.model.PostModel;
import com.springmvcapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
//@RequiredArgsConstructor
public class HomeController {

    @GetMapping
    public String getHomePage() {
        return "home_page";
    }


}
