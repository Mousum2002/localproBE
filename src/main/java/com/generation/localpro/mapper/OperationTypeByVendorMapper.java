package com.generation.localpro.mapper;

import com.generation.localpro.dto.OperationTypeByVendorDTO;
import com.generation.localpro.model.OperationTypeByVendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.generation.localpro.model.OperationType;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OperationTypeByVendorMapper {

    @Mapping(source = "operationType.id",          target = "operationTypeId")
    @Mapping(source = "operationType.name",        target = "operationTypeName")
    @Mapping(source = "operationType.description", target = "operationTypeDescription")
    @Mapping(source = "user.userName",             target = "vendorUserName")
    @Mapping(source = "user.city",                 target = "vendorCity")
    OperationTypeByVendorDTO toDto(OperationTypeByVendor entity);

    @Mapping(target = "id",            ignore = true)
    @Mapping(target = "user",          ignore = true)
    @Mapping(target = "operationType", source = "operationTypeId", qualifiedByName = "toOperationTypeShell")
    OperationTypeByVendor toEntity(OperationTypeByVendorDTO dto);

    @Named("toOperationTypeShell")
    default OperationType toOperationTypeShell(Integer id) {
        if (id == null) return null;
        OperationType ot = new OperationType();
        ot.setId(id);
        return ot;
    }
}