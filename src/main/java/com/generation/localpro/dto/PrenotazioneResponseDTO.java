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
//a cosa serve tutto sti campi?

    // vendor info
    private Integer vendorId;
    private String  vendorUserName;
    private String  vendorCity;          // aggiunto

    // service info
    private Integer serviceId;
    private String  serviceOperationName;
    private String  serviceDescription;  // aggiunto
    private Integer servicePrice;

    // client info
    private Integer userId;
    private String  userName;

    private LocalDateTime reservationDate;
    private String status;
    private String note;
}