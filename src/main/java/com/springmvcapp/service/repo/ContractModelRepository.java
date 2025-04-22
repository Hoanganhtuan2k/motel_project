package com.springmvcapp.service.repo;

import com.springmvcapp.model.ContractModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractModelRepository extends JpaRepository<ContractModel,Long> {

}
