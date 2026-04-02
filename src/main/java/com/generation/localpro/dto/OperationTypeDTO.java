package com.generation.localpro.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationTypeDTO {

   private Integer id; // null on POST (auto-generated), populated in response

    @NotBlank(message = "Il nome dell'operazione non può essere vuoto")
    private String name;

    @NotEmpty(message = "Inserisci almeno un tag descrittivo")
    private List<String> tags;

    @NotBlank(message = "La descrizione è obbligatoria")
    private String description;

}