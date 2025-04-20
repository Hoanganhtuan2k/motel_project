package com.springmvcapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springmvcapp.status.MotelStatus;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

/**
 * DTO for {@link com.springmvcapp.model.MotelModel}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MotelModelDto implements Serializable {

  private Long id;
  private String name;
  private String status;
  private String currentContractId;
  private String adminId;
  private MultipartFile imageFile;
  private Double lat;
  private Double lng;

}