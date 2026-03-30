package com.generation.localpro.mapper;

import org.mapstruct.Mapper;
import com.generation.localpro.dto.PortalUserResponseDTO;
import com.generation.localpro.model.PortalUser;

import org.mapstruct.Mapping;
import com.generation.localpro.dto.PortalUserRequestDTO;;

@Mapper(componentModel = "spring", uses = {ReviewMapper.class})
public interface PortalUserMapper {

    @Mapping(target = "banned", source = "banned")
    @Mapping(target = "roles",  source = "roles")
    @Mapping(target = "reviews", source = "reviews")  // ← add this
    PortalUserResponseDTO toResponseDto(PortalUser entity);

    @Mapping(target = "id",                 ignore = true)
    @Mapping(target = "reviews",            ignore = true)
    @Mapping(target = "operationsProvided", ignore = true)
    PortalUser toEntity(PortalUserRequestDTO dto);
}