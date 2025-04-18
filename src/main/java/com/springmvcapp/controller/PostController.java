package com.springmvcapp.controller;

import com.springmvcapp.dto.PostModelDto;
import com.springmvcapp.model.PostModel;
import com.springmvcapp.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

  public static final String USER_LOGIN = "userLogin";
  private final PostService postService;
  @GetMapping
  public String getAllMotels(Model model,
      @AuthenticationPrincipal UserDetails userDetails) {
    String username = userDetails.getUsername();

    model.addAttribute(USER_LOGIN, username);
    List<PostModel> motels = postService.getAllPosts();
    model.addAttribute("userRooms", motels);
    return "post";
  }

  @PostMapping("/create")
  public String createPost(@ModelAttribute PostModelDto post) {
    postService.savePost(post); // Lưu bài viết mới vào database
    return "redirect:/";       // Điều hướng về trang Home
  }
}
