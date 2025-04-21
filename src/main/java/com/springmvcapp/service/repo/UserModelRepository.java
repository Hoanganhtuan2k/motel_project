package com.springmvcapp.service.repo;

import com.springmvcapp.dto.UserModelDto;
import com.springmvcapp.model.UserModel;

import java.util.List;
import java.util.Optional;

import com.springmvcapp.status.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserModelRepository extends JpaRepository<UserModel, Long> {
  Optional<UserModel> findByEmail(String email);

  Optional<UserModel> findByUsername(String username);

  List<UserModel> findByRole(UserRole role);
}
