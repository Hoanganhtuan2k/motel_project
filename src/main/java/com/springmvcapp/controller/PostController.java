package com.springmvcapp.controller;

import com.springmvcapp.dto.PostModelDto;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.model.PostModel;
import com.springmvcapp.model.UserModel;
import com.springmvcapp.service.PostService;

import java.time.LocalDateTime;
import java.util.List;

import com.springmvcapp.service.repo.PostModelRepository;
import com.springmvcapp.service.repo.UserModelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private ModelMapper modelMapper;

    private final PostService postService;
    private final UserModelRepository userModelRepository;
    private final PostModelRepository postModelRepository;
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
    public String createPostWidthAllMotel(@ModelAttribute PostModelDto post,
                                          @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        UserModel user = userModelRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        post.setAdminId(String.valueOf(user.getId()));
        postService.savePost(post);
        return "redirect:/";

    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute PostModelDto postDto,
                             @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();
        UserModel user = userModelRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        postDto.setAdminId(String.valueOf(user.getId()));

        postService.savePost(postDto);
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

    @GetMapping("/edit/{id}")
    public String showEditPostForm(@PathVariable Long id, Model model) {
        PostModel post = postService.getPostById(id);
        PostModelDto postDto = modelMapper.map(post, PostModelDto.class);
        postDto.setId(post.getId());


        model.addAttribute("postDto", postDto);

        MotelModel motel = postService.getMotelById(post.getRelatedRoomId());
        model.addAttribute("motel", motel);
        return "post_templates/edit_post";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute PostModelDto postDto,
                           @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        UserModel user = userModelRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        postDto.setAdminId(String.valueOf(user.getId()));

        // Ensure the postDto has an id
        if (postDto.getId() == null) {
            throw new IllegalArgumentException("Post ID is required for update");
        }

        postService.updatePost(postDto);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id,
                             @AuthenticationPrincipal UserDetails userDetails) {
//        String email = userDetails.getUsername();
//        UserModel user = userModelRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found: " + email));
//
//        PostModel post = postService.getPostById(id);

        postService.deletePostById(id);
        return "redirect:/";
    }




}
