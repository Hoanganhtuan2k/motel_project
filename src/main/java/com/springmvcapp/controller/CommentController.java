package com.springmvcapp.controller;

import com.springmvcapp.dto.CommentModelDto;
import com.springmvcapp.model.CommentModel;
import com.springmvcapp.model.UserModel;
import com.springmvcapp.service.CommentService;
import com.springmvcapp.service.repo.UserModelRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserModelRepository userModelRepository;

    @GetMapping("/list")
    public String showCommentList(Model model) {
        List<CommentModel> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);
        return "home_page"; // Tên của file Thymeleaf template
    }

    @GetMapping("/create")
    public String createComment(@ModelAttribute CommentModel comment,
                                @AuthenticationPrincipal UserDetails userDetails,
                                RedirectAttributes redirectAttributes) {
        String email = userDetails.getUsername();
        UserModel user = userModelRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found: " + email));

        comment.setUserId(String.valueOf(user.getId()));
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        commentService.saveComment(comment);

        redirectAttributes.addFlashAttribute("message", "Comment added successfully!");
        return "redirect:/comments/list"; // Chuyển hướng đến danh sách bình luận
    }

    @PostMapping("/add")
    public String addComment(@RequestParam String userId,
                             @RequestParam String postId,
                             @RequestParam String content,
                             @RequestParam int rating) {
        CommentModel newComment = new CommentModel();
        newComment.setUserId(userId);
        newComment.setPostId("1");
        newComment.setContent(content);
        newComment.setStar(rating);

        commentService.saveComment(newComment);
        return "redirect:/comments/" + postId;
    }

    @GetMapping("/{postId}")
    public String getComments(@PathVariable String postId, Model model) {
        List<CommentModelDto> comments = commentService.getCommentsByPostId(postId);
        model.addAttribute("comments", comments);
        return "home_page"; // Điều hướng đến trang comments.html
    }
}
