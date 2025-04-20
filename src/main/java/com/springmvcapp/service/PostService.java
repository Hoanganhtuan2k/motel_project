package com.springmvcapp.service;

import com.springmvcapp.dto.PostModelDto;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.model.PostModel;
import com.springmvcapp.service.repo.MotelModelRepository;
import com.springmvcapp.service.repo.PostModelRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private MotelModelRepository motelModelRepository;


    @Autowired
    private ModelMapper modelMapper;

    public List<PostModel> getAllPosts() {
        return postRepository.findAll();
    }
    private static List<PostModel> posts;

    public void savePost(PostModelDto postDTO) {
        PostModel postModel = modelMapper.map(postDTO, PostModel.class);
        postModel.setCreatedAt(LocalDateTime.now());
        postModel.setUpdatedAt(LocalDateTime.now());
        postRepository.save(postModel);
    }

    public List<MotelModel> getAllMotels() {
        return motelModelRepository.findAll();
    }

    public PostModel getPostById(Long id) {
        return postRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy bài đăng với ID: " + id));
    }

    public MotelModel getMotelById(Long motelId) {
        return motelModelRepository.findById(motelId)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy phòng trọ với ID: " + motelId));
    }

}
