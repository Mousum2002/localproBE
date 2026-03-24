package com.generation.localpro.dto;

import com.generation.localpro.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    private int id;

    // Utile per sapere chi ha creato/gestisce questo tipo di operazione
    @NotNull(message = "L'ID utente è obbligatorio")
    private int userId;

    @NotBlank(message = "Il nome dell'operazione non può essere vuoto")
    private String name;

    // NotEmpty assicura che la lista non sia null e abbia almeno un elemento
    @NotEmpty(message = "Inserisci almeno un tag descrittivo")
    private List<String> tags;

    @NotBlank(message = "La descrizione è obbligatoria")
    private String description;
    
    private Status status;
    
}