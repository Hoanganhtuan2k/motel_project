package com.springmvcapp.model;

import com.springmvcapp.status.ContractStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
  private ContractStatus status; // ACTIVE, TERMINATED, EXPIRED
}
