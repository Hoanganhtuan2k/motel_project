package com.springmvcapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LocationModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name; // Tên khu vực (VD: "Quận Hoàn Kiếm")
  private Double latitude; // Kinh độ
  private Double longitude; // Vĩ độ
  private Double radius; // Bán kính tìm kiếm
}
