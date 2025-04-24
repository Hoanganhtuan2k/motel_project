package com.springmvcapp.controller;

import com.springmvcapp.dto.ContractModelDto;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.model.UserModel;
import com.springmvcapp.service.ContractService;
import com.springmvcapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

  @Autowired
  private ModelMapper modelMapper;

  @GetMapping("/create")
  public String createContract(Model model) {
    List<UserModel> users = contractService.getAllUser();
    List<MotelModel> motels = contractService.getAllRooms();

    model.addAttribute("users", users);
    model.addAttribute("rooms", motels);
    model.addAttribute("contract", new ContractModelDto());

    return "contract_templates/contract_create";
  }


  @PostMapping("/save")
  public String saveContract(@ModelAttribute("contract") ContractModelDto contractDto,
                             Model model) {
    contractService.saveContract(contractDto);
    return "redirect:/contracts/contract";
  }

  @GetMapping("/contract")
  public String getAllContracts(
          @RequestParam(required = false) String searchKeyword,
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "10") int size,
          Model model
  ) {
    Page<ContractModelDto> contracts = contractService.getAllContracts(searchKeyword, page, size);

    model.addAttribute("contracts", contracts.getContent());
    model.addAttribute("currentPage", page);
    model.addAttribute("totalPages", contracts.getTotalPages());
    model.addAttribute("searchKeyword", searchKeyword);

    return "contract_templates/contract";
  }

  @GetMapping("/edit/{id}")
  public String editContractForm(@PathVariable("id") Long id, Model model) {
    ContractModelDto contractDto = contractService.getContractById(id);
    List<UserModel> users = contractService.getAllUser();
    List<MotelModel> motels = contractService.getAllRooms();
    System.out.println(contractDto.getStartDate());

    model.addAttribute("contract", contractDto);
    model.addAttribute("users", users);
    model.addAttribute("rooms", motels);

    return "contract_templates/contract_edit";
  }


  @PostMapping("/update")
  public String updateContract(@ModelAttribute("contract") ContractModelDto contractDto) {
    contractService.updateContract(contractDto);
    return "redirect:/contracts/contract";
  }


  @GetMapping("/{id}")
  public String viewContractDetail(@PathVariable("id") Long id, Model model) {
    ContractModelDto contractDto = contractService.getContractById(id);
    model.addAttribute("contract", contractDto);
    return "contract_templates/contract_detail";
  }

  @GetMapping("/delete/{id}")
  public String deleteContract(@PathVariable("id") Long id) {
    contractService.deleteContractById(id);
    return "redirect:/contracts/contract";
  }


}
