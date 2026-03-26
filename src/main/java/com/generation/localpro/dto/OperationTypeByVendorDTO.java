package com.generation.localpro.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationTypeByVendorDTO {

     private Integer id;


    @NotNull(message = "L'ID del tipo di operazione è obbligatorio")
    private Integer operationTypeId;

    @Min(value = 0, message = "Il prezzo non può essere negativo")
    private int price;
    
}