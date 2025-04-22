package com.springmvcapp.controller;

import com.springmvcapp.model.PostModel;
import com.springmvcapp.model.UserModel;
import com.springmvcapp.service.PostService;
import com.springmvcapp.service.repo.UserModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    @Autowired
    private PostService postService;

    private final UserModelRepository userModelRepository;
    /**
     * Trang chủ hiển thị danh sách bài viết có phân trang và tìm kiếm theo từ khóa.
     * @param page     trang hiện tại (mặc định = 1)
     * @param keyword  từ khóa tìm kiếm (tuỳ chọn)
     */
    @GetMapping
    public String getHomePage(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(required = false) String keyword,
                              Model model) {
        int pageSize = 10;
        Page<PostModel> postPage = postService.searchPostsWithMotels(keyword, page - 1, pageSize);
        var posts = postPage.getContent();

        // Map chứa ID bài viết => tên người đăng
        Map<Long, String> postAdminNames = new HashMap<>();
        for (PostModel post : posts) {
            Long adminId = Long.valueOf(post.getAdminId());
            Optional<UserModel> admin = userModelRepository.findById(adminId);
            postAdminNames.put(post.getId(), admin.map(UserModel::getUsername).orElse("Không rõ"));
        }

        model.addAttribute("posts", posts);
        model.addAttribute("adminNames", postAdminNames); // truyền thêm map này
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postPage.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "home_page";
    }


    @GetMapping("/profile")
    public String userProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        System.out.println(username);
        UserModel user = userModelRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        model.addAttribute("user", user);
        return "profile";
    }


}
