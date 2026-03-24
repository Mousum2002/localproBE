package com.generation.localpro.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.generation.localpro.Service.PortalUserService;
import com.generation.localpro.dto.PortalUserDTO;
import com.generation.localpro.mapper.PortalUserMapper;
import com.generation.localpro.model.PortalUser;
import com.generation.localpro.model.Role;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class PortalUserController {

    private final PortalUserService portalUserService;
    private final PortalUserMapper portalUserMapper;

    public PortalUserController(PortalUserService portalUserService, PortalUserMapper portalUserMapper) {
        this.portalUserService = portalUserService;
        this.portalUserMapper = portalUserMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PortalUserDTO create(@Valid @RequestBody PortalUserDTO portalUserDto) {
        PortalUser created = portalUserService.create(portalUserMapper.toEntity(portalUserDto));
        return portalUserMapper.toDto(created);
    }

    @PutMapping("/{id}")
    public PortalUserDTO update(@PathVariable Integer id, @Valid @RequestBody PortalUserDTO portalUserDto) {
        PortalUser updated = portalUserService.update(id, portalUserMapper.toEntity(portalUserDto));
        return portalUserMapper.toDto(updated);
    }

    @GetMapping("/{id}")
    public PortalUserDTO getById(@PathVariable Integer id) {
        return portalUserMapper.toDto(portalUserService.getById(id));
    }

    @GetMapping
    public List<PortalUserDTO> getAll(@RequestParam(required = false) Role role) {
        List<PortalUser> users;
        if (role != null) {
            users = portalUserService.getByRole(role);
        } else {
            users = portalUserService.getAll();
        }
        return users.stream().map(portalUserMapper::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        portalUserService.delete(id);
    }
}
