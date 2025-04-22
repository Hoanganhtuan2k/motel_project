package com.springmvcapp.controller;

import com.springmvcapp.dto.ContractModelDto;
import com.springmvcapp.dto.PostModelDto;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.model.UserModel;
import com.springmvcapp.service.ContractService;
import com.springmvcapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/contracts")
public class ContractController {
  @Autowired
  private PostService postService;

  private final ContractService contractService;

//  @GetMapping("/create/{postId}")
//  public String createContract(@PathVariable Long postId, Model model) {
//    PostModelDto post = postService.getPostDetailById(postId);
//    MotelModel room = contractService.getRoomFromPost(postId);
//
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    String currentUsername = authentication.getName();
//    boolean isAdmin = authentication.getAuthorities().stream()
//            .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
//    System.out.println(isAdmin);
//    ContractModelDto contractDto = new ContractModelDto();
//    if (room != null) {
//      contractDto.setRoomId(String.valueOf(room.getId()));
//      contractDto.setFinalPrice(room.getActualPrice());
//    }
//
//    if (isAdmin) {
//      List<UserModel> users = contractService.getAllStudents();
//      model.addAttribute("users", users);
//    } else {
//      UserModel currentUser = contractService.getUserByUsername(currentUsername);
//      contractDto.setStudentId(String.valueOf(currentUser.getId()));
//      model.addAttribute("currentUser", currentUser);
//    }
//
//    model.addAttribute("contract", contractDto);
//    model.addAttribute("post", post);
//    model.addAttribute("room", room);
//    model.addAttribute("isAdmin", isAdmin);
//
//    return "contract_create";
//  }



  @PostMapping("/save")
  public String saveContract(@ModelAttribute("contract") ContractModelDto contractDto,
                             Model model) {
    contractService.saveContract(contractDto);
    return "redirect:/";
  }

}
