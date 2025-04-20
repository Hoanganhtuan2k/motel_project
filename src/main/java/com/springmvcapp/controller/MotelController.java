package com.springmvcapp.controller;

import com.springmvcapp.dto.MotelModelDto;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.service.MotelService;
import com.springmvcapp.service.repo.MotelModelRepository;

import com.springmvcapp.status.MotelStatus;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/motels")
@RequiredArgsConstructor
public class MotelController {

    @Autowired
    private MotelModelRepository motelModelRepository;

    public static final String USER_LOGIN = "userLogin";
    private final MotelService motelService;

    @GetMapping
    public String getAllMotels(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<MotelModel> motels = motelModelRepository.findAll();
        model.addAttribute("motels", motels);
        return "motel";
    }

    @GetMapping("/create")
    public String showCreateRoomForm(Model model) {
        MotelModelDto modelDto = new MotelModelDto();
        model.addAttribute("motelDto", new MotelModelDto());
        return "motel_templates/create_motel";
    }

    @PostMapping("/create")
    public String creatMotel(MotelModelDto modelDto) {
        MultipartFile image = modelDto.getImageFile();
        Date createAt = new Date();
        String storgeFileName = createAt.getTime() + "_" + image.getOriginalFilename();
        try {
            String uploadDir = "static/images/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storgeFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex) {
            System.out.println("Exception" + ex.getMessage());
        }

        MotelModel motelModel = new MotelModel();
        motelModel.setName(modelDto.getName());
        motelModel.setStatus(MotelStatus.valueOf(modelDto.getStatus()));
        motelModel.setCurrentContractId(modelDto.getCurrentContractId());
        motelModel.setAdminId(modelDto.getAdminId());
        motelModel.setImageName(storgeFileName);
        motelModel.setCreatedAt(LocalDateTime.now());
        motelModel.setLat(modelDto.getLat());
        motelModel.setLng(modelDto.getLng());
        motelModelRepository.save(motelModel);
        return "redirect:/motels";
    }


}
