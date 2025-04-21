package com.springmvcapp.service;

import com.springmvcapp.dto.MotelModelDto;
import com.springmvcapp.dto.PostModelDto;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.model.PostModel;
import com.springmvcapp.service.repo.MotelModelRepository;
import com.springmvcapp.service.repo.PostModelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page; // ✅ CHỈNH Ở ĐÂY
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public void updatePost(PostModelDto dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Post ID is required for update");
        }

        PostModel existing = postRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        existing.setTitle(dto.getTitle());
        existing.setContent(dto.getContent());
        existing.setRelatedRoomId(dto.getRelatedRoomId());
        existing.setStatus(dto.getStatus());
        existing.setUpdatedAt(LocalDateTime.now());

        postRepository.save(existing);
    }


    public void deletePostById(Long id) {
        if (!postRepository.existsById(id)) {
            throw new EntityNotFoundException("Không tìm thấy bài viết với ID: " + id);
        }
        postRepository.deleteById(id);
    }

    public Page<PostModel> searchPosts(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        if (keyword != null && !keyword.isEmpty()) {
            return postRepository.findByTitleContainingIgnoreCase(keyword, pageable);
        } else {
            return postRepository.findAll(pageable);
        }
    }

    public PostModelDto getPostDetailById(Long postId) {
        PostModel post = postRepository.findById(postId).orElse(null);
        if (post == null) return null;

        PostModelDto postDto = modelMapper.map(post, PostModelDto.class);

        if (post.getRelatedRoomId() != null) {
            try {
                Long roomId = Long.valueOf(post.getRelatedRoomId());
                MotelModel motel = motelModelRepository.findById(roomId).orElse(null);
                if (motel != null) {
                    MotelModelDto motelDto = modelMapper.map(motel, MotelModelDto.class);
                    postDto.setMotel(motelDto);
                }
            } catch (NumberFormatException e) {
                // Optional: log
            }
        }

        return postDto;
    }
}
