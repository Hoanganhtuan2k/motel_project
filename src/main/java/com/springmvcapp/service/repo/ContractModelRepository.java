package com.springmvcapp.service.repo;

import com.springmvcapp.model.ContractModel;
import org.apache.http.annotation.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractModelRepository extends JpaRepository<ContractModel,Long> {

    @Query(value = "SELECT c.* FROM contract_model c " +
            "JOIN user_model u ON c.student_id = u.id " +
            "JOIN motel_model m ON CAST(c.room_id AS UNSIGNED) = m.id " +
            "WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%'))",
            countQuery = "SELECT COUNT(*) FROM contract_model c " +
                    "JOIN user_model u ON c.student_id = u.id " +
                    "JOIN motel_model m ON CAST(c.room_id AS UNSIGNED) = m.id " +
                    "WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(m.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%'))",
            nativeQuery = true)
    Page<ContractModel> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);



}
