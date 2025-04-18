package com.springmvcapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * DTO for {@link com.springmvcapp.model.PostModel}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostModelDto implements Serializable {

  private Long ownerId;
  private String title;
  private String content;
  private Long motelId;
}