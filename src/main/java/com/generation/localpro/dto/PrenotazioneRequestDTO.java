package com.generation.localpro.dto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrenotazioneRequestDTO { @NotNull(message = "L'ID del venditore è obbligatorio")
    private Integer vendorId;

    @NotNull(message = "L'ID del servizio è obbligatorio")
    private Integer serviceId;

    @NotNull(message = "La data di prenotazione è obbligatoria")
    private LocalDateTime reservationDate;

    private String status;

    @NotNull(message = "L'ID dell'utente è obbligatorio")
    private Integer userId;}