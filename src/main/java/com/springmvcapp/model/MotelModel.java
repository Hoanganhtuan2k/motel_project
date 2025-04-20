package com.springmvcapp.model;

import com.springmvcapp.status.MotelStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MotelModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private MotelStatus status; // EMPTY, OCCUPIED
  private String currentContractId; // dùng để lookup contract hiện tại
  private String adminId; // phòng do ai quản lý
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String imageName;
  private Double lat;
  private Double lng;
  private String description;
  private String acreage;
  private String originalPrice;
  private String actualPrice;


}
