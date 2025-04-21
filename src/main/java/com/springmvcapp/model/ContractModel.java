package com.springmvcapp.model;

import com.springmvcapp.status.ContractStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ContractModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String studentId;
  private String roomId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String finalPrice;
  private String description;

  @Enumerated(EnumType.STRING)
  private ContractStatus status;
}
