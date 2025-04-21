package com.springmvcapp.service.repo;

import com.springmvcapp.model.PostModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostModelRepository extends JpaRepository<PostModel, Long> {
    Page<PostModel> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    List<PostModel> findByRelatedRoomId(Long relatedRoomId);

}