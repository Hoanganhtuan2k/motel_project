package com.springmvcapp.controller;

import com.springmvcapp.dto.PostModelDto;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.model.PostModel;
import com.springmvcapp.service.PostService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

  public static final String USER_LOGIN = "userLogin";
  private final PostService postService;
  @GetMapping("/create/motel")
  public String getAllMotels(Model model,
      @AuthenticationPrincipal UserDetails userDetails) {
    String username = userDetails.getUsername();
    model.addAttribute("userLogin", username);

    List<MotelModel> motels = postService.getAllMotels();
    model.addAttribute("motels", motels);

    PostModelDto postDto = new PostModelDto();
    model.addAttribute("postDto", postDto);

    return "post";
  }


  @PostMapping("/create/motel")
  public String createPostWidthAllMotel(@ModelAttribute PostModelDto post) {
    postService.savePost(post);
    return "redirect:/";

  }  @PostMapping("/create")
  public String createPost(@ModelAttribute PostModelDto post) {
    postService.savePost(post);
    return "redirect:/";
  }

  @GetMapping("/create")
  public String showCreatePostForm(@RequestParam(required = false) Long motelId, Model model) {
    PostModelDto postDto = new PostModelDto();

    if (motelId != null) {
      MotelModel motel = postService.getMotelById(motelId);
      System.out.println(motel);
      postDto.setRelatedRoomId(motel.getId().toString());
      model.addAttribute("motel", motel);
    }
    model.addAttribute("postDto", postDto);
    return "post_templates/create_post";
  }


  @GetMapping("/{id}")
  public String viewPostDetail(@PathVariable Long id, Model model) {
    PostModel post = postService.getPostById(id);
    model.addAttribute("post", post);
    return "post_detail";
  }
}
