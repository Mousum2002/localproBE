package com.generation.localpro.mapper;


import com.generation.localpro.dto.OperationTypeByVendorDTO;
import com.generation.localpro.model.OperationTypeByVendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OperationTypeByVendorMapper {

    // Flatten nested objects to IDs for the response
    @Mapping(source = "user.id", target = "vendorId")
    @Mapping(source = "operationType.id", target = "operationTypeId")
    OperationTypeByVendorDTO toDto(OperationTypeByVendor entity);

    // user and operationType are resolved in the Service via repository lookups
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "operationType", ignore = true)
    OperationTypeByVendor toEntity(OperationTypeByVendorDTO dto);
}
