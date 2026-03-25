package com.generation.localpro.mapper;


import com.generation.localpro.dto.OperationTypeDTO;
import com.generation.localpro.model.OperationType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OperationTypeMapper {

    OperationTypeDTO toDto(OperationType entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "operationsProvided", ignore = true)
    OperationType toEntity(OperationTypeDTO dto);
}
