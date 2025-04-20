package com.springmvcapp.controller;

import com.springmvcapp.model.PostModel;
import com.springmvcapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private PostService postService;

    /**
     * Trang chủ hiển thị danh sách bài viết có phân trang và tìm kiếm theo từ khóa.
     * @param page     trang hiện tại (mặc định = 1)
     * @param keyword  từ khóa tìm kiếm (tuỳ chọn)
     */
    @GetMapping
    public String getHomePage(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(required = false) String keyword,
                              Model model) {
        int pageSize = 10; // Số bài viết mỗi trang

        // Gọi service để lấy dữ liệu phân trang (Spring dùng chỉ số trang từ 0)
        Page<PostModel> postPage = postService.searchPosts(keyword, page - 1, pageSize);

        model.addAttribute("posts", postPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", postPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        System.out.println("Total Pages: " + postPage.getTotalPages());

        return "home_page";
    }
}
