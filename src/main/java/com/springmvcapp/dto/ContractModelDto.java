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
  private Long id;
  private String studentId;
  private String studentName; // ✅ NEW

  private String roomId;
  private String roomName; // ✅ NEW

  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private String finalPrice;
  private String description;
  private ContractStatus status;

  public String getStatusVi() {
    switch (this.status) {
      case ACTIVE: return "Đang thuê";
      case TERMINATED: return "Đã hủy";
      case EXPIRED: return "Hết hạn";
      default: return "Không rõ";
    }
  }


}