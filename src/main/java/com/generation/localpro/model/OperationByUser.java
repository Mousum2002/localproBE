package com.generation.localpro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationByUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // chi richiede il servizio
    @ManyToOne
    @JoinColumn(name = "client_id")
    private PortalUser client;

    // il servizio offerto dal professionista
    @ManyToOne
    @JoinColumn(name = "operation_by_vendor_id")
    private OperationTypeByVendor operationTypeByVendor;

    private LocalDateTime requestedAt;

    @Enumerated(EnumType.STRING)
    private OperationStatus status;

    private String notes;
}