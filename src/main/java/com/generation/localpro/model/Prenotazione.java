package com.generation.localpro.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Il venditore è obbligatorio")   
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")                    
    private PortalUser vendor;

    @NotNull(message = "Il servizio è obbligatorio")   
    @ManyToOne
    @JoinColumn(name = "service_id")
    private OperationTypeByVendor service;

    @Column(nullable = false)
    private LocalDateTime reservationDate;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private PortalUser user;
}
