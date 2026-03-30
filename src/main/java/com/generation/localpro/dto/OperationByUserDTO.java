package com.generation.localpro.dto;

import com.generation.localpro.model.OperationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationByUserDTO {

    private Integer id;

    // chi ha richiesto
    private Integer clientId;
    private String clientUserName;

    // il servizio
    private Integer operationByVendorId;
    private String operationTypeName;
    private String vendorUserName;
    private int price;

    private LocalDateTime requestedAt;
    private OperationStatus status;
    private String notes;
}