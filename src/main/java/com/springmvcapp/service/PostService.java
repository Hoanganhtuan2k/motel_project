package com.springmvcapp.service;

import com.springmvcapp.dto.PostModelDto;
import com.springmvcapp.model.PostModel;
import com.springmvcapp.service.repo.PostModelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostModelRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<PostModel> getAllPosts() {
        return postRepository.findAll(); // Truy vấn tất cả bài viết từ database
    }
    private static List<PostModel> posts;

    public void savePost(PostModelDto postDTO) {
        PostModel postModel = modelMapper.map(postDTO, PostModel.class);
        postModel.setCreatedAt(LocalDateTime.now()); // Thêm thời gian tạo bài viết
        postModel.setUpdatedAt(LocalDateTime.now()); // Thêm thời gian cập nhật bài viết
        postRepository.save(postModel);
    }
}
