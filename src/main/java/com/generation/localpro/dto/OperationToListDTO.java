package com.generation.localpro.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationToListDTO {
    
    private Integer id;

    @NotNull(message = "Lo username non puo essere vuoto")
    private String userName;

    @NotNull(message = "La coordinata x non puo essere vuota")
    private Integer x;

    @NotNull(message = "La coordinata y non puo essere vuota")
    private Integer y;

    @NotNull(message = "La media dei rating non puo essere vuota")
    private int ratingAvg;

    @NotNull(message = "Il prezzo non puo essere vuoto")
    private int price;

    @NotNull(message = "La descrizione non puo essere vuota")
    private String description;

}
