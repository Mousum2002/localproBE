package com.generation.localpro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneResponseDTO {

    private Integer id;

    // vendor info (flat — no full PortalUser to avoid cycles)
    private Integer vendorId;
    private String vendorUserName;


    private Integer serviceId;
    private String serviceOperationName;
    private Integer servicePrice;

    // client info
    private Integer userId;
    private String userUserName;

    private LocalDateTime reservationDate;
    private String status;
}