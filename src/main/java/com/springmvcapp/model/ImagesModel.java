package com.springmvcapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "images")
public class ImagesModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String imageUrl;      // Đường dẫn lưu ảnh (có thể là URL hoặc file path)

  @Column(nullable = false)
  private Long motelId;         // ID của Motel mà ảnh này thuộc về

  private String description;   // Mô tả ảnh (tùy chọn)

}