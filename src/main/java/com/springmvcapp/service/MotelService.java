package com.springmvcapp.service;

import com.springmvcapp.dto.MotelModelDto;
import com.springmvcapp.model.ImagesModel;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.service.repo.ImagesModelRepository;
import com.springmvcapp.service.repo.MotelModelRepository;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class MotelService {

  @Autowired
  private MotelModelRepository motelModelRepository;
  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private ImagesModelRepository imagesModelRepository;
  private static List<MotelModel> motels;


  public List<MotelModel> getAllMotels() {
    return motelModelRepository.findAll();
  }

  public List<MotelModelDto> getAllMotelsWithImages() {
    List<MotelModel> motels = motelModelRepository.findAll();
    List<MotelModelDto> motelDTOs = new ArrayList<>();

    for (MotelModel motel : motels) {
      MotelModelDto motelDTO = modelMapper.map(motel,
          MotelModelDto.class); // Chuyển từ MotelModel sang MotelDTO

      // Lấy hình ảnh của motel
      List<ImagesModel> images = imagesModelRepository.findByMotelId(motel.getId());

      // Lấy các URL của hình ảnh
      List<String> imageUrls = images.stream()
          .map(ImagesModel::getImageUrl)
          .collect(Collectors.toList());

      motelDTO.setImageUrls(imageUrls); // Gắn danh sách hình ảnh vào DTO

      motelDTOs.add(motelDTO);
    }

    return motelDTOs;
  }
}
