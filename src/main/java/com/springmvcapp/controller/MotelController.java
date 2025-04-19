package com.springmvcapp.controller;

import com.springmvcapp.dto.MotelModelDto;
import com.springmvcapp.model.ImagesModel;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.service.ImageUploadService;
import com.springmvcapp.service.MotelService;
import com.springmvcapp.service.repo.ImagesModelRepository;
import com.springmvcapp.service.repo.MotelModelRepository;
import com.springmvcapp.status.MotelStatus;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/motels")
@RequiredArgsConstructor
public class MotelController {

  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private MotelModelRepository motelModelRepository;
  @Autowired
  private ImageUploadService imageUploadService;
  @Autowired
  private ImagesModelRepository imagesModelRepository;

  public static final String USER_LOGIN = "userLogin";
  private final MotelService motelService;

//    @GetMapping
//    public String showMotels(Model model,
//                             @AuthenticationPrincipal UserDetails userDetails) {
//        // Return the name of the view (e.g., motels.html)
//        String username = userDetails.getUsername();
//
//        model.addAttribute(USER_LOGIN, username);
//
//        List<MotelModel> books = motelService.getAllMotels();
//        model.addAttribute("userRooms", books);
//        return "motel";
//    }

//    @GetMapping("/edit/{title}")
//    public String getEditBookPage(Model model, @PathVariable String title) {
//        MotelModel byTitle = motelService.findByTitleAndDelete(title);
//        model.addAttribute("motelToEdit", byTitle);
//        return "motel_templates/edit_motel";
//    }

  @GetMapping
  public String getAllMotels(Model model,
      @AuthenticationPrincipal UserDetails userDetails) {
    String username = userDetails.getUsername();

//    model.addAttribute(USER_LOGIN, username);
//    List<MotelModel> motels = motelService.getAllMotels();
//    model.addAttribute("userRooms", motels);
    // Lấy danh sách phòng trọ kèm hình ảnh dưới dạng DTO
    List<MotelModelDto> motels = motelService.getAllMotelsWithImages();

    model.addAttribute("userRooms", motels); // Trả về danh sách DTO trong model
    return "motel";
  }

  @GetMapping("/rooms/edit/{id}")
  public String editRoom(@PathVariable Long id, Model model) {
//        MotelModel room = MotelService.findById(id); // Replace with actual method to fetch the room
//        model.addAttribute("roomToEdit", room); // Bind the object to the Thymeleaf template
    return "motel_templates/edit_motel"; // Name of the Thymeleaf template (editRoom.html)
  }

  @GetMapping("/create")
  public String showCreateRoomForm(Model model) {
    model.addAttribute("motel", new MotelModel()); // khởi tạo object trống để binding form
    return "motel_templates/create_motel"; // trỏ tới file Thymeleaf
  }

  @PostMapping("/create")
  public String createMotel(
      @ModelAttribute MotelModelDto motelDto,
      @RequestParam("images") List<MultipartFile> images
  ) throws IOException {

    // 1. Map và lưu phòng trọ
    MotelModel motel = modelMapper.map(motelDto, MotelModel.class);
    motel.setCreatedAt(LocalDateTime.now());
    motel = motelModelRepository.save(motel);

    // 2. Upload ảnh bằng service
    List<String> uploadedPaths = imageUploadService.saveImages(images);

    // 3. Lưu vào DB
    for (String path : uploadedPaths) {
      ImagesModel image = new ImagesModel();
      image.setMotelId(motel.getId());
      image.setImageUrl(path);
      imagesModelRepository.save(image);
    }

    return "redirect:/motels";
  }



}
