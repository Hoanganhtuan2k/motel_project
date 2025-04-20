package com.springmvcapp.controller;

import com.springmvcapp.dto.MotelModelDto;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.service.MotelService;
import com.springmvcapp.service.repo.MotelModelRepository;

import com.springmvcapp.status.MotelStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Date;

@Controller
@RequestMapping("/motels")
@RequiredArgsConstructor
public class MotelController {

    @Autowired
    private MotelModelRepository motelModelRepository;

    public static final String USER_LOGIN = "userLogin";
    private final MotelService motelService;

    @GetMapping
    public String listMotels(@RequestParam(defaultValue = "1") int page,
                             @RequestParam(required = false) String keyword,
                             Model model) {
        Page<MotelModel> motelPage;

        if (keyword != null && !keyword.isEmpty()) {
            System.out.println(keyword);
            motelPage = motelService.searchMotels(keyword, page);
        } else {
            motelPage = motelService.getAllMotels(page);
        }

        model.addAttribute("motels", motelPage.getContent());
        model.addAttribute("totalPages", motelPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
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

    @GetMapping("/detail/{id}")
    public String showMotelDetail(@PathVariable Long id, Model model) {
        MotelModel motel = motelService.getMotelById(id);
        model.addAttribute("motel", motel);
        return "motel_templates/detail_motel";
    }
    @GetMapping("/edit/{id}")
    public String showMotelEdit(@PathVariable Long id, Model model) {
        MotelModel motel = motelService.getMotelById(id);
        model.addAttribute("motel", motel);
        return "motel_templates/edit_motel";
    }


}
