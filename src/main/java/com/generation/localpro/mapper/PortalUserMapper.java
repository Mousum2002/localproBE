package com.generation.localpro.mapper;

import org.springframework.stereotype.Component;

import com.generation.localpro.dto.PortalUserDTO;
import com.generation.localpro.model.PortalUser;

@Component
public class PortalUserMapper {

    public PortalUserDTO toDto(PortalUser entity) {
        if (entity == null) {
            return null;
        }
        return PortalUserDTO.builder()
            .id(entity.getId())
            .firstName(entity.getFirstName())
            .lastName(entity.getLastName())
            .email(entity.getEmail())
            .password(entity.getPassword())
            .city(entity.getCity())
            .address(entity.getAddress())
            .role(entity.getRole())
            .bio(entity.getBio())
            .x(entity.getX())
            .y(entity.getY())
            .build();
    }

    public PortalUser toEntity(PortalUserDTO dto) {
        if (dto == null) {
            return null;
        }
        PortalUser entity = new PortalUser();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setCity(dto.getCity());
        entity.setAddress(dto.getAddress());
        entity.setRole(dto.getRole());
        entity.setBio(dto.getBio());
        entity.setX(dto.getX());
        entity.setY(dto.getY());
        return entity;
    }
}
