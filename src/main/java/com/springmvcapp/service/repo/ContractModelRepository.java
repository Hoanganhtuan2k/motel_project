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

//    @Query("SELECT c FROM CommentModel c WHERE c.content LIKE %:keyword% OR c.id LIKE %:keyword%")
//    Page<ContractModel> findByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
