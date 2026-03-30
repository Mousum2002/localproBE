package com.generation.localpro.mapper;

import com.generation.localpro.dto.OperationByUserDTO;
import com.generation.localpro.model.OperationByUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OperationByUserMapper {

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientUserName", source = "client.userName")
    @Mapping(target = "operationByVendorId", source = "operationTypeByVendor.id")
    @Mapping(target = "operationTypeName", source = "operationTypeByVendor.operationType.name")
    @Mapping(target = "vendorUserName", source = "operationTypeByVendor.user.userName")
    @Mapping(target = "price", source = "operationTypeByVendor.price")
    OperationByUserDTO toDto(OperationByUser entity);

    @Mapping(target = "client", ignore = true)
    @Mapping(target = "operationTypeByVendor", ignore = true)
    OperationByUser toEntity(OperationByUserDTO dto);
}