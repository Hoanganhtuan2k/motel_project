package com.springmvcapp.model;

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
public class RentalModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long motelId; // Liên kết đến phòng trọ (Khóa ngoại)
  private Long tenantId; // Liên kết đến sinh viên thuê (Khóa ngoại)
  private LocalDateTime startDate; // Ngày bắt đầu thuê
  private LocalDateTime endDate; // Ngày kết thúc thuê
  private Double totalAmount; // Tổng số tiền thuê
  private Boolean isActive; // Trạng thái thuê (đang thuê hoặc đã kết thúc)
}
