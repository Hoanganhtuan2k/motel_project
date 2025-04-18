package com.springmvcapp.service;

import com.springmvcapp.model.PostModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private static List<PostModel> posts;

    static {
        posts = new ArrayList<>();
        posts.add(new PostModel(
                1L, 101L, "Phòng trọ tiện nghi gần trường Đại học",
                "Phòng rộng 25m², đầy đủ tiện ích: điều hòa, nóng lạnh, giường, tủ.",
                3000000.0, "123 Ngõ 25, Nguyễn Văn Cừ, Hà Nội",
                "0987654321", LocalDateTime.now().minusDays(7), LocalDateTime.now().minusDays(2)
        ));
        posts.add(new PostModel(
                2L, 102L, "Phòng trọ giá rẻ gần bến xe Mỹ Đình",
                "Phòng sạch sẽ, diện tích 15m², thích hợp cho sinh viên.",
                2000000.0, "Số 15 Ngõ 45, Phạm Hùng, Hà Nội",
                "0976543210", LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(5)
        ));
        posts.add(new PostModel(
                3L, 103L, "Phòng trọ cao cấp gần Hồ Tây",
                "Phòng diện tích 30m², nội thất sang trọng, ban công view Hồ Tây.",
                5000000.0, "Căn hộ A12, Ven Hồ Tây, Hà Nội",
                "0901234567", LocalDateTime.now().minusDays(3), LocalDateTime.now()
        ));
        posts.add(new PostModel(
                4L, 104L, "Phòng trọ cho nữ gần Đại học Quốc Gia",
                "Phòng 20m², đầy đủ tiện ích, khu vực an toàn dành cho nữ.",
                2500000.0, "Số 8 Ngách 3, Xuân Thủy, Hà Nội",
                "0912345678", LocalDateTime.now().minusDays(1), LocalDateTime.now()
        ));
    }


    public List<PostModel> getAllPosts() {
        // Trả về danh sách bài viết (fake data)
        return posts; // `posts` là danh sách static được khởi tạo ở trên
    }
}
