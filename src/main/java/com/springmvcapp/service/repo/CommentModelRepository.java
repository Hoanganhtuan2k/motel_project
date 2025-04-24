package com.springmvcapp.service.repo;

import com.springmvcapp.model.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CommentModelRepository extends JpaRepository<CommentModel, Long> {
    List<CommentModel> findByRelatedRoomId(String roomId);

    List<CommentModel> findByPostId(String postId);
}