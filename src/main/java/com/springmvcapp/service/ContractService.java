package com.springmvcapp.service;


import com.springmvcapp.dto.ContractModelDto;
import com.springmvcapp.model.ContractModel;
import com.springmvcapp.model.MotelModel;
import com.springmvcapp.model.PostModel;
import com.springmvcapp.model.UserModel;
import com.springmvcapp.service.repo.ContractModelRepository;
import com.springmvcapp.service.repo.MotelModelRepository;
import com.springmvcapp.service.repo.PostModelRepository;
import com.springmvcapp.service.repo.UserModelRepository;
import com.springmvcapp.status.MotelStatus;
import com.springmvcapp.status.UserRole;
import lombok.RequiredArgsConstructor;
import org.apache.http.annotation.Contract;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final PostModelRepository postModelRepository;
    private final MotelModelRepository motelModelRepository;
    private final UserModelRepository userModelRepository;
    private final ContractModelRepository contractModelRepository;
    private final ModelMapper modelMapper;

    public List<UserModel> getAllStudents() {
        return userModelRepository.findByRole(UserRole.USER);
    }

    public MotelModel getRoomFromPost(Long postId) {
        Optional<PostModel> postOpt = postModelRepository.findById(postId);
        if (postOpt.isPresent()) {
            Long roomId = postOpt.get().getRelatedRoomId();
            return motelModelRepository.findById(roomId).orElse(null);
        }
        return null;
    }

    public void saveContract(ContractModelDto dto) {
        ContractModel contract = new ContractModel();
        contract.setStudentId(dto.getStudentId());
        contract.setRoomId(dto.getRoomId());
        contract.setStartDate(dto.getStartDate());
        contract.setEndDate(dto.getEndDate());
        contract.setFinalPrice(dto.getFinalPrice());
        contract.setDescription(dto.getDescription());
        contract.setStatus(dto.getStatus());

        contract = contractModelRepository.save(contract);

        Optional<MotelModel> motelOpt = motelModelRepository.findById(Long.parseLong(dto.getRoomId()));
        if (motelOpt.isPresent()) {
            MotelModel motel = motelOpt.get();
            motel.setStatus(MotelStatus.OCCUPIED);
            motel.setCurrentContractId(String.valueOf(contract.getId()));
            motelModelRepository.save(motel);
        }
    }

    public UserModel getUserByUsername(String username) {
        return userModelRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


//    public Page<ContractModelDto> getAllContracts(String searchKeyword, int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<ContractModel> contractPage;
//
//        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
////            contractPage = contractModelRepository.findByKeyword(searchKeyword, pageable);
//        } else {
//            contractPage = contractModelRepository.findAll(pageable);
//        }
//
//        return contractPage.map(contract -> modelMapper.map(contract, ContractModelDto.class));
//    }
}
