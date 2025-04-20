package com.springmvcapp.service;

import com.springmvcapp.dto.MotelModelDto;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.service.repo.MotelModelRepository;
import java.util.ArrayList;
import java.util.stream.Collectors;
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


  public Page<MotelModel> getAllMotels(int page) {
    Pageable pageable = PageRequest.of(page - 1, 5); // 5 là số item mỗi trang
    return motelModelRepository.findAll(pageable);
  }


  public MotelModel getMotelById(Long id) {
    return motelModelRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng trọ với ID: " + id));
  }

  public Page<MotelModel> searchMotels(String keyword, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    if (keyword != null && !keyword.trim().isEmpty()) {
      return motelModelRepository.findByNameContainingIgnoreCase(keyword.trim(), pageable);
    } else {
      return motelModelRepository.findAll(pageable);
    }
  }

  public Page<MotelModel> searchMotels(String keyword, int page) {
    Pageable pageable = PageRequest.of(page - 1, 5);
    return motelModelRepository.searchByKeyword(keyword, pageable);
  }



}
