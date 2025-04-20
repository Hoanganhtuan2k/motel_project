package com.springmvcapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springmvcapp.status.ContractStatus;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * DTO for {@link com.springmvcapp.model.ContractModel}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractModelDto implements Serializable {

  private String studentId;
  private String roomId;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private ContractStatus status;
}