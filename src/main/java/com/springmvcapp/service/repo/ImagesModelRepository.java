package com.springmvcapp.service.repo;

import com.springmvcapp.model.ImagesModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesModelRepository extends JpaRepository<ImagesModel, Long> {

  List<ImagesModel> findByMotelId(Long id);
}