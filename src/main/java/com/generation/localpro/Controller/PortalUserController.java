package com.generation.localpro.Controller;


import com.generation.localpro.dto.PortalUserRequestDTO;
import com.generation.localpro.dto.PortalUserResponseDTO;
import com.generation.localpro.mapper.PortalUserMapper;
import com.generation.localpro.model.PortalUser;
import com.generation.localpro.Service.PortalUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class PortalUserController {

    private final PortalUserService portalUserService;
    private final PortalUserMapper portalUserMapper;

    public PortalUserController(PortalUserService portalUserService, PortalUserMapper portalUserMapper) {
        this.portalUserService = portalUserService;
        this.portalUserMapper = portalUserMapper;
    }


    @PutMapping("/{id}")
    public PortalUserResponseDTO update(@PathVariable Integer id,
                                        @Valid @RequestBody PortalUserRequestDTO requestDto) {
        PortalUser updated = portalUserService.update(id, portalUserMapper.toEntity(requestDto));
        return portalUserMapper.toResponseDto(updated);
    }

    @GetMapping("/{id}")
    public PortalUserResponseDTO getById(@PathVariable Integer id) {
        return portalUserMapper.toResponseDto(portalUserService.getById(id));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        portalUserService.delete(id);
    }
}
