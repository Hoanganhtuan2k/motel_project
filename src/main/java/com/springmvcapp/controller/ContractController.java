package com.springmvcapp.controller;

import com.springmvcapp.dto.ContractModelDto;
import com.springmvcapp.dto.PostModelDto;
import com.springmvcapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ContractController {
  @Autowired
  private PostService postService;

  @GetMapping("/contracts/create/{postId}")
  public String createContract(@PathVariable Long postId, Model model) {
    PostModelDto post = postService.getPostDetailById(postId);
    model.addAttribute("post", post);
    model.addAttribute("contract", new ContractModelDto()); // Assuming you have a DTO for contracts
    return "contract_create"; // Return the contract creation template
  }
}
