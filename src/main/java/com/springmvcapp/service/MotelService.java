package com.springmvcapp.service;

import com.springmvcapp.dto.MotelModelDto;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.service.repo.MotelModelRepository;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotelService {

  @Autowired
  private MotelModelRepository motelModelRepository;
  @Autowired
  private ModelMapper modelMapper;


  public List<MotelModel> getAllMotels() {
    return motelModelRepository.findAll();
  }

  public MotelModel getMotelById(Long id) {
    return motelModelRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy phòng trọ với ID: " + id));
  }

}
