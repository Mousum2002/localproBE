package com.generation.localpro.mapper;

import com.generation.localpro.dto.PrenotazioneRequestDTO;
import com.generation.localpro.dto.PrenotazioneResponseDTO;
import com.generation.localpro.model.Prenotazione;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrenotazioneMapper {

    @Mapping(source = "vendor.id",                        target = "vendorId")
    @Mapping(source = "vendor.userName",                  target = "vendorUserName")
    @Mapping(source = "vendor.city",                      target = "vendorCity")
    @Mapping(source = "service.id",                       target = "serviceId")
    @Mapping(source = "service.operationType.name",       target = "serviceOperationName")
    @Mapping(source = "service.operationType.description",target = "serviceDescription")
    @Mapping(source = "service.price",                    target = "servicePrice")
    @Mapping(source = "user.id",                          target = "userId")
    @Mapping(source = "user.userName",                    target = "userUserName")
    PrenotazioneResponseDTO toResponseDto(Prenotazione entity);

    @Mapping(target = "id",              ignore = true)
    @Mapping(target = "vendor",          ignore = true)
    @Mapping(target = "service",         ignore = true)
    @Mapping(target = "user",            ignore = true)
    @Mapping(target = "reservationDate", ignore = true)
    Prenotazione toEntity(PrenotazioneRequestDTO dto);
}