package com.springmvcapp.service;

import com.springmvcapp.dto.CommentModelDto;
import com.springmvcapp.model.CommentModel;
import com.springmvcapp.service.repo.CommentModelRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentModelRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CommentModel> getCommentsByRoomId(String roomId) {
        return commentRepository.findByRelatedRoomId(roomId);
    }

    public void saveComment(CommentModelDto commentDTO, HttpServletRequest httpServletRequest) {
//        Cookie cookie = httpServletRequest.getCookies();
        CommentModel comment = modelMapper.map(commentDTO, CommentModel.class);
//        comment.setUserId(httpServletRequest.getCookies().get);
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);
    }

    public void saveComment(CommentModel comment) {
        commentRepository.save(comment);
    }

    public List<CommentModel> getAllComments() {
        return commentRepository.findAll();
    }
}
