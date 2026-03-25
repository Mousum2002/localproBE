package com.generation.localpro.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.generation.localpro.Service.PortalUserService;
import com.generation.localpro.dto.PortalUserRequestDTO;
import com.generation.localpro.dto.PortalUserResponseDTO;
import com.generation.localpro.model.PortalUser;

import jakarta.validation.Valid;
import com.generation.localpro.mapper.PortalUserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/public")
public class PublicController {

      private final PortalUserService portalUserService;
      private final PortalUserMapper portalUserMapper;

      public PublicController(PortalUserService portalUserService, PortalUserMapper portalUserMapper){
        this.portalUserService = portalUserService;
        this.portalUserMapper = portalUserMapper;
      }
     @PostMapping
    public ResponseEntity<PortalUserResponseDTO> create(@Valid @RequestBody PortalUserRequestDTO requestDto) {
        PortalUser created = portalUserService.create(portalUserMapper.toEntity(requestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(portalUserMapper.toResponseDto(created));
    }
    
}
