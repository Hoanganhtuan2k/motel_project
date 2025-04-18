package com.springmvcapp.service.repo;

import com.springmvcapp.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostModelRepository extends JpaRepository<PostModel, Long> {

}