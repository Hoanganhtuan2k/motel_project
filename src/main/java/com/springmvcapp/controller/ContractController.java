package com.springmvcapp.controller;

import com.springmvcapp.dto.ContractModelDto;
import com.springmvcapp.dto.PostModelDto;
import com.springmvcapp.model.ContractModel;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.model.UserModel;
import com.springmvcapp.service.ContractService;
import com.springmvcapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/contracts")
public class ContractController {
  @Autowired
  private PostService postService;

  private final ContractService contractService;

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping("/create/{postId}")
  public String createContract(@PathVariable Long postId, Model model) {
    PostModelDto post = postService.getPostDetailById(postId);
    MotelModel room = contractService.getRoomFromPost(postId);

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentUsername = authentication.getName();
    boolean isAdmin = authentication.getAuthorities().stream()
            .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    System.out.println(isAdmin);
    ContractModelDto contractDto = new ContractModelDto();
    if (room != null) {
      contractDto.setRoomId(String.valueOf(room.getId()));
      contractDto.setFinalPrice(room.getActualPrice());
    }

    if (isAdmin) {
      List<UserModel> users = contractService.getAllStudents();
      model.addAttribute("users", users);
    } else {
      UserModel currentUser = contractService.getUserByUsername(currentUsername);
      contractDto.setStudentId(String.valueOf(currentUser.getId()));
      model.addAttribute("currentUser", currentUser);
    }

    model.addAttribute("contract", contractDto);
    model.addAttribute("post", post);
    model.addAttribute("room", room);
    model.addAttribute("isAdmin", isAdmin);

    return "contract_create";
  }



  @PostMapping("/save")
  public String saveContract(@ModelAttribute("contract") ContractModelDto contractDto,
                             Model model) {
    contractService.saveContract(contractDto);
    return "redirect:/";
  }

  @GetMapping("/contract")
  public String redirectToContractPage() {
    return "contract_templates/contract"; // Trả về tên file contract.html (đã cấu hình trong Thymeleaf)
  }

//  @GetMapping("/all")
//  public String getAllContracts(Model model) {
//    List<ContractModel> contracts = contractService.getAllContracts();
////    List<ContractModelDto> contractDtos = contracts.stream()
////            .map(contract -> modelMapper.map(contract, ContractModelDto.class))
////            .collect(Collectors.toList());
//
//    model.addAttribute("contracts", contracts); // Truyền danh sách vào model
//    return "contract_templates/contract"; // Hiển thị contract.html
//  }


//@GetMapping
//public String getAllContracts(
//        @RequestParam(required = false) String searchKeyword,
//        @RequestParam(defaultValue = "0") int page,
//        @RequestParam(defaultValue = "10") int size,
//        Model model) {
//
//  Page<ContractModelDto> contracts = contractService.getAllContracts(searchKeyword, page, size);
//  model.addAttribute("contracts", contracts.getContent());
//  model.addAttribute("currentPage", page);
//  model.addAttribute("totalPages", contracts.getTotalPages());
//  model.addAttribute("searchKeyword", searchKeyword);
//    return "contract_templates/contract"; // Tên file Thymeleaf
//  }

}
