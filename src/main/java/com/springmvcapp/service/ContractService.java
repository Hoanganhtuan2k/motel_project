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
import org.springframework.data.domain.PageImpl;
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

    public List<UserModel> getAllUser() {
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

    public List<MotelModel> getAllRooms() {
        return motelModelRepository.findAll();
    }

    public Page<ContractModelDto> getAllContracts(String searchKeyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ContractModel> contractPage;

        // Sử dụng repository search nếu có từ khóa
        if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            contractPage = contractModelRepository.searchByKeyword(searchKeyword, pageable);
        } else {
            contractPage = contractModelRepository.findAll(pageable);
        }

        // Map dữ liệu sang DTO và gán thêm tên người dùng + tên phòng
        return contractPage.map(contract -> {
            ContractModelDto dto = modelMapper.map(contract, ContractModelDto.class);

            // Lấy tên người thuê (studentName)
            userModelRepository.findById(Long.valueOf(contract.getStudentId()))
                    .ifPresent(user -> dto.setStudentName(user.getUsername()));

            // Lấy tên phòng (roomName)
            motelModelRepository.findById(Long.parseLong(contract.getRoomId()))
                    .ifPresent(motel -> dto.setRoomName(motel.getName()));

            return dto;
        });
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

    public ContractModelDto getContractById(Long id) {
        Optional<ContractModel> contractOpt = contractModelRepository.findById(id);

        if (contractOpt.isPresent()) {
            ContractModel contract = contractOpt.get();
            ContractModelDto dto = modelMapper.map(contract, ContractModelDto.class);

            userModelRepository.findById(Long.valueOf(contract.getStudentId()))
                    .ifPresent(user -> dto.setStudentName(user.getUsername()));

            motelModelRepository.findById(Long.parseLong(contract.getRoomId()))
                    .ifPresent(room -> dto.setRoomName(room.getName()));

            return dto;
        }

        return null;
    }

    public void updateContract(ContractModelDto dto) {
        Optional<ContractModel> optionalContract = contractModelRepository.findById(dto.getId());

        if (optionalContract.isPresent()) {
            ContractModel contract = optionalContract.get();

            contract.setStudentId(dto.getStudentId());
            contract.setRoomId(dto.getRoomId());
            contract.setStartDate(dto.getStartDate());
            contract.setEndDate(dto.getEndDate());
            contract.setFinalPrice(dto.getFinalPrice());
            contract.setDescription(dto.getDescription());
            contract.setStatus(dto.getStatus());

            contractModelRepository.save(contract);
        }
    }

    public void deleteContractById(Long id) {
        contractModelRepository.deleteById(id);
    }



}
