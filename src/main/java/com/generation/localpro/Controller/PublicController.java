package com.generation.localpro.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.generation.localpro.mapper.PortalUserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


 @RequiredArgsConstructor

@RestController
@RequestMapping("/public")
public class PublicController {

       private final PortalUserService portalUserService;
    private final PortalUserMapper portalUserMapper;
    private final OperationTolistService operationService;
    private final AuthenticationManager authenticationManager; 

   
     @PostMapping("/register")
    public ResponseEntity<PortalUserResponseDTO> registerUser(@Valid @RequestBody PortalUserRequestDTO requestDto,HttpServletRequest request) {
        PortalUser created = portalUserService.create(portalUserMapper.toEntity(requestDto));

           UsernamePasswordAuthenticationToken token =
            new UsernamePasswordAuthenticationToken(requestDto.getUserName(), requestDto.getPassword());
            token.setDetails(new WebAuthenticationDetails(request));
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

         request.getSession(true).setAttribute(
            HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context
        );


       return ResponseEntity.status(HttpStatus.CREATED).body(portalUserMapper.toResponseDto(created));
    }
    
    @GetMapping
    public ResponseEntity<List<PortalUserResponseDTO>> getAll() {
      List<PortalUserResponseDTO> response = portalUserService.getAll();
      return ResponseEntity.ok(response);
    }

    @GetMapping("/allOperationList")
      public ResponseEntity<List<OperationToListDTO>> getAllOperation(@RequestParam(required = false) String city) {
    return ResponseEntity.ok(operationService.getAll(city));
}
}
