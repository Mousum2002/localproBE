package com.generation.localpro.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {

    private int id;

    @NotNull(message = "L'ID dell'utente recensito è obbligatorio")
    private Integer userId;

    @Min(value = 1, message = "Il rating minimo è 1")
    @Max(value = 5, message = "Il rating massimo è 5")
    private int rating;

    @NotBlank(message = "La descrizione della recensione non può essere vuota")
    private String description;
    
    // Campi extra utili per il Frontend
    private String userFullName; // Per mostrare subito chi è stato recensito
}