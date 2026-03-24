package com.generation.localpro.mapper;

import org.springframework.stereotype.Component;

import com.generation.localpro.dto.OperationTypeByVendorDTO;
import com.generation.localpro.model.OperationTypeByVendor;

@Component
public class OperationTypeByVendorMapper {

    public OperationTypeByVendorDTO toDto(OperationTypeByVendor entity) {
        if (entity == null) return null;
        return OperationTypeByVendorDTO.builder()
            .id(entity.getId())
            .vendorId(entity.getUser() != null ? entity.getUser().getId() : null)
            .operationTypeId(entity.getOperationType() != null ? entity.getOperationType().getId() : null)
            .price(entity.getPrice())
            .build();
    }

    public OperationTypeByVendor toEntity(OperationTypeByVendorDTO dto) {
        if (dto == null) return null;
        OperationTypeByVendor entity = new OperationTypeByVendor();
        entity.setId(dto.getId());
        entity.setPrice(dto.getPrice());
        // user e operationType vengono impostati nel Service
        return entity;
    }
}
