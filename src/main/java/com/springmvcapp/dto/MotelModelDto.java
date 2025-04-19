package com.springmvcapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springmvcapp.status.MotelStatus;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * DTO for {@link com.springmvcapp.model.MotelModel}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MotelModelDto implements Serializable {

  private String name;
  private MotelStatus status;
  private String currentContractId;
  private String adminId;
}