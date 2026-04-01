package com.generation.localpro.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneRequestDTO { 

    @NotNull(message = "L'ID del servizio è obbligatorio")
    private Integer serviceId;

    private String note;
    private String status;

    // data preferita dal cliente
    private LocalDateTime reservationDate;
}