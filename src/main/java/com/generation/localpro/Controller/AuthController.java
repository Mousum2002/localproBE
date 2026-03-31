package com.generation.localpro.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.localpro.Service.PortalUserService;
import com.generation.localpro.dto.PortalUserResponseDTO;
import com.generation.localpro.mapper.PortalUserMapper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final PortalUserService userService;
    private final PortalUserMapper portalUserMapper;

    public AuthController(PortalUserService userService, PortalUserMapper portalUserMapper) {
        this.userService = userService;
        this.portalUserMapper = portalUserMapper;
    }

    @GetMapping("/me")
    public ResponseEntity<PortalUserResponseDTO> getCurrentUser(Authentication auth) {
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(
            portalUserMapper.toResponseDto(userService.findByUserName(auth.getName()))
        );
    }
}