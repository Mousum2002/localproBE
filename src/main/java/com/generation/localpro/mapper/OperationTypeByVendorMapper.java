package com.generation.localpro.mapper;

import org.springframework.stereotype.Component;

import com.generation.localpro.dto.OperationTypeByVendorDTO;
import com.generation.localpro.model.OperationTypeByVendor;

@Component
public class OperationTypeByVendorMapper {

    public OperationTypeByVendorDTO toDto(OperationTypeByVendor entity) {
        if (entity == null) {
            return null;
        }
        return OperationTypeByVendorDTO.builder()
            .id(entity.getId())
            .vendorId(entity.getVendorId())
            .operationTypeId(entity.getOperationTypeId())
            .price(entity.getPrice())
            .build();
    }

    public OperationTypeByVendor toEntity(OperationTypeByVendorDTO dto) {
        if (dto == null) {
            return null;
        }
        OperationTypeByVendor entity = new OperationTypeByVendor();
        entity.setId(dto.getId());
        entity.setVendorId(dto.getVendorId());
        entity.setOperationTypeId(dto.getOperationTypeId());
        entity.setPrice(dto.getPrice());
        return entity;
    }
}
