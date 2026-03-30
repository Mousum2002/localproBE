package com.generation.localpro.mapper;

import org.mapstruct.Mapper;
import com.generation.localpro.dto.PortalUserResponseDTO;
import com.generation.localpro.model.PortalUser;

import org.mapstruct.Mapping;
import com.generation.localpro.dto.PortalUserRequestDTO;;

@Mapper(componentModel = "spring")
public interface PortalUserMapper {

    @Mapping(target = "banned", source = "banned") // explicit after rename
    PortalUserResponseDTO toResponseDto(PortalUser entity);

    @Mapping(target = "id",                  ignore = true)
    @Mapping(target = "reviews",             ignore = true)
    @Mapping(target = "operationsProvided",  ignore = true)
    PortalUser toEntity(PortalUserRequestDTO dto);
}