package com.springmvcapp.service.repo;

import com.springmvcapp.model.MotelModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MotelModelRepository extends JpaRepository<MotelModel, Long> {
    Optional<MotelModel> findById(Long id);
    Page<MotelModel> findByNameContainingIgnoreCase(String keyword, Pageable pageable);
    // Trong Repository
    @Query("SELECT m FROM MotelModel m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<MotelModel> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
    
}