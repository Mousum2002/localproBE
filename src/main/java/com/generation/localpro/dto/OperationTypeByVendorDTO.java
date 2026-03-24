package com.generation.localpro.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationTypeByVendorDTO {

    private int id;

    @NotNull(message = "L'ID del fornitore è obbligatorio")
    private Integer vendorId;

    @NotNull(message = "L'ID del tipo di operazione è obbligatorio")
    private Integer operationTypeId;

    @Min(value = 0, message = "Il prezzo non può essere negativo")
    private int price;
    
}