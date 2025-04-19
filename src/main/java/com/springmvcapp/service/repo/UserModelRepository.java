package com.springmvcapp.service.repo;

import com.springmvcapp.model.UserModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserModelRepository extends JpaRepository<UserModel, Long> {
  public UserModel findByEmail(String email);


  Optional<UserModel> findByUsername(String username);
}
