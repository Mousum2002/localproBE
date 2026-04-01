package com.generation.localpro.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.localpro.Service.OperationTolistService;
import com.generation.localpro.Service.OperationTypeService;
import com.generation.localpro.Service.ReviewService;
import com.generation.localpro.dto.OperationTypeDTO;
import com.generation.localpro.mapper.OperationTypeMapper;
import com.generation.localpro.mapper.ReviewMapper;
import com.generation.localpro.Service.PortalUserService;
import com.generation.localpro.dto.OperationToListDTO;
import com.generation.localpro.dto.PortalUserRequestDTO;
import com.generation.localpro.dto.PortalUserResponseDTO;
import com.generation.localpro.dto.ReviewResponseDTO;
import com.generation.localpro.dto.VendorProfileDTO;
import com.generation.localpro.mapper.PortalUserMapper;
import com.generation.localpro.model.PortalUser;
import com.generation.localpro.repository.OperationTypeByVendorRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
    private final OperationTypeByVendorRepository vendorOperationRepository;
    private final AuthenticationManager authenticationManager;
    private final OperationTypeService operationTypeService;
    private final OperationTypeMapper operationTypeMapper;
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @PostMapping("/register")
    public ResponseEntity<PortalUserResponseDTO> registerUser(
            @Valid @RequestBody PortalUserRequestDTO requestDto,
            HttpServletRequest request) {

        PortalUser created = portalUserService.create(portalUserMapper.toEntity(requestDto));

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(requestDto.getUserName(), requestDto.getPassword());
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        request.getSession(true).setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

        return ResponseEntity.status(HttpStatus.CREATED).body(portalUserMapper.toResponseDto(created));
    }

    @GetMapping("/allOperationList")
    public ResponseEntity<List<OperationToListDTO>> getAllOperation(
            @RequestParam(required = false) String city) {
        return ResponseEntity.ok(operationService.getAll(city));
    }

    @GetMapping("/vendor/{id}")
    public ResponseEntity<VendorProfileDTO> getVendorProfile(@PathVariable Integer id) {

        // 1. prendo l'utente dal service che già hai
        PortalUser user = portalUserService.getById(id);

        // 2. prendo tutti i suoi servizi dal repository che già hai
        List<VendorProfileDTO.VendorServiceDTO> services = vendorOperationRepository
                .findByUserId(id)
                .stream()
                .map(op -> VendorProfileDTO.VendorServiceDTO.builder()
                        .id(op.getId())
                        .category(op.getOperationType().getName())
                        .description(op.getOperationType().getDescription())
                        .price(op.getPrice())
                        .build())
                .toList();

        // 3. calcolo la media delle recensioni
        int ratingAvg = 0;
        if (user.getReviews() != null && !user.getReviews().isEmpty()) {
            ratingAvg = (int) Math.round(
                    user.getReviews().stream()
                            .mapToInt(r -> r.getRating())
                            .average()
                            .orElse(0.0));
        }

        // 4. mappo le recensioni nel formato DTO
        List<ReviewResponseDTO> reviews = user.getReviews() == null
                ? List.of()
                : user.getReviews().stream()
                        .map(r -> new ReviewResponseDTO(
                                r.getId(), r.getUser().getId(), null, r.getRating(), r.getDescription()))
                        .toList();

        // 5. assemblo il VendorProfileDTO e lo restituisco
        VendorProfileDTO profile = VendorProfileDTO.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .profileImage(user.getProfileImage())
                .bio(user.getBio())
                .city(user.getCity())
                .address(user.getAddress())
                .latitude(user.getX())
                .longitude(user.getY())
                .ratingAvg(ratingAvg)
                .reviews(reviews)
                .services(services)
                .build();

        return ResponseEntity.ok(profile);
    }

    // ── ENDPOINT PUBBLICI AGGIUNTIVI ────────────────────────

    // Lista categorie — usata da create-service e profile-page senza auth
    @GetMapping("/operation-types")
    public ResponseEntity<List<OperationTypeDTO>> getAllOperationTypes() {
        return ResponseEntity.ok(
            operationTypeService.getAll().stream()
                .map(operationTypeMapper::toDto)
                .toList()
        );
    }

    // Recensioni di un vendor — usata da profile-page senza auth
    @GetMapping("/reviews/vendor/{vendorName}")
    public ResponseEntity<List<ReviewResponseDTO>> getVendorReviews(@PathVariable String vendorName) {
        return ResponseEntity.ok(
            reviewService.getVendorReviews(vendorName).stream()
                .map(reviewMapper::toResponseDto)
                .toList()
        );
    }
}