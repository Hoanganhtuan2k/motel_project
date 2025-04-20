package com.springmvcapp.service.repo;

import com.springmvcapp.model.MotelModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotelModelRepository extends JpaRepository<MotelModel, Long> {
    Optional<MotelModel> findById(Long id);

}