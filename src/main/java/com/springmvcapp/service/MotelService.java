package com.springmvcapp.service;

import com.springmvcapp.dto.MotelModelDto;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.model.PostModel;
import com.springmvcapp.service.repo.MotelModelRepository;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.springmvcapp.service.repo.PostModelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotelService {

  @Autowired
  private MotelModelRepository motelModelRepository;
  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private PostModelRepository postModelRepository;


  public Page<MotelModel> getAllMotels(int page) {
    Pageable pageable = PageRequest.of(page - 1, 5); // 5 là số item mỗi trang
    return motelModelRepository.findAll(pageable);
  }


  public MotelModel getMotelById(Long id) {
    return motelModelRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng trọ với ID: " + id));
  }

  public Page<MotelModel> searchMotels(String keyword, int page) {
    Pageable pageable = PageRequest.of(page - 1, 5);
    return motelModelRepository.searchByKeyword(keyword, pageable);
  }

  public void deleteMotelById(Long id) {
    // Kiểm tra xem Motel có tồn tại không
    motelModelRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Không tìm thấy motel với ID: " + id));

    // Tìm danh sách bài viết liên kết với Motel
    List<PostModel> relatedPosts = postModelRepository.findByRelatedRoomId(id);

    // Xóa danh sách bài viết liên kết (nếu có)
    if (!relatedPosts.isEmpty()) {
      postModelRepository.deleteAll(relatedPosts);
    }

    // Xóa Motel
    motelModelRepository.deleteById(id);
  }
}
