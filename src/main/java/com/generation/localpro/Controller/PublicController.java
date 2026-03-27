package com.generation.localpro.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.generation.localpro.Service.OperationTolistService;
import com.generation.localpro.Service.PortalUserService;
import com.generation.localpro.dto.OperationToListDTO;
import com.generation.localpro.dto.PortalUserRequestDTO;
import com.generation.localpro.dto.PortalUserResponseDTO;
import com.generation.localpro.model.PortalUser;

import jakarta.validation.Valid;

import com.generation.localpro.mapper.PortalUserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/public")
public class PublicController {

      private final PortalUserService portalUserService;
      private final PortalUserMapper portalUserMapper;
      private final OperationTolistService operationService;

      public PublicController(PortalUserService portalUserService, PortalUserMapper portalUserMapper, OperationTolistService operationService){
        this.portalUserService = portalUserService;
        this.portalUserMapper = portalUserMapper;
        this.operationService = operationService;
      }
     @PostMapping("/register")
    public ResponseEntity<PortalUserResponseDTO> registerUser(@Valid @RequestBody PortalUserRequestDTO requestDto) {
        PortalUser created = portalUserService.create(portalUserMapper.toEntity(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(portalUserMapper.toResponseDto(created));
    }
    
    @GetMapping
    public ResponseEntity<List<PortalUserResponseDTO>> getAll() {
      List<PortalUser> users = portalUserService.getAll();
      List<PortalUserResponseDTO> response = users.stream()
            .map(portalUserMapper::toResponseDto)
            .collect(Collectors.toList());
    return ResponseEntity.ok(response);
    }

    @GetMapping("/AllOpeationList")
    public ResponseEntity<List<OperationToListDTO>> getAllOperation() {
        return ResponseEntity.ok(operationService.getAll());
    }
}
