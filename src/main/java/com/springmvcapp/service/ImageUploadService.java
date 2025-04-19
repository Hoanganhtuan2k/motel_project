package com.springmvcapp.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageUploadService {

  private final String UPLOAD_DIR = "uploads/motels/";

  public List<String> saveImages(List<MultipartFile> images) throws IOException {
    List<String> imagePaths = new ArrayList<>();

    if (images != null && !images.isEmpty()) {
      Path uploadPath = Paths.get(UPLOAD_DIR);
      if (!Files.exists(uploadPath)) {
        Files.createDirectories(uploadPath);
      }

      for (MultipartFile file : images) {
        if (!file.isEmpty()) {
          String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
          Path filePath = uploadPath.resolve(fileName);
          Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
          imagePaths.add("/" + UPLOAD_DIR + fileName); // để sử dụng trong HTML
        }
      }
    }

    return imagePaths;
  }
}
