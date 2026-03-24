package com.generation.localpro.mapper;

import org.springframework.stereotype.Component;

import com.generation.localpro.dto.OperationTypeDTO;
import com.generation.localpro.model.OperationType;

@Component
public class OperationTypeMapper {

    public OperationTypeDTO toDto(OperationType entity) {
        if (entity == null) {
            return null;
        }
        return OperationTypeDTO.builder()
            .id(entity.getId())
            .userId(entity.getUserId())
            .name(entity.getName())
            .tags(entity.getTags())
            .description(entity.getDescription())
            .status(entity.getStatus())
            .build();
    }

    public OperationType toEntity(OperationTypeDTO dto) {
        if (dto == null) {
            return null;
        }
        OperationType entity = new OperationType();
        entity.setId(dto.getId());
        entity.setUserId(dto.getUserId());
        entity.setName(dto.getName());
        entity.setTags(dto.getTags());
        entity.setDescription(dto.getDescription());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
