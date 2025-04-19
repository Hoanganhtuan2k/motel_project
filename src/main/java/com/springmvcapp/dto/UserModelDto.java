package com.springmvcapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springmvcapp.status.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * DTO for {@link com.springmvcapp.model.UserModel}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModelDto implements Serializable {

  private String username;
  private String password;
  private String email;
  private String phone;
  @Enumerated(EnumType.STRING)
  private UserRole role;
  private String confirmPassword;

}