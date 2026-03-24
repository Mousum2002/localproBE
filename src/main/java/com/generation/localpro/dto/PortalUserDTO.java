package com.generation.localpro.dto;

import com.generation.localpro.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // Comodissimo per mappare l'entity al DTO
public class PortalUserDTO {

    private int id;

    @NotBlank(message = "Il nome è obbligatorio")
    private String firstName;

    @NotBlank(message = "Il cognome è obbligatorio")
    private String lastName;

    @Email(message = "Inserire un indirizzo email valido")
    @NotBlank(message = "L'email è obbligatoria")
    private String email;

    // La password la teniamo nel DTO SOLO se questo DTO 
    // viene usato per la registrazione/login (input).
    // Se è un DTO di risposta, questo campo va rimosso o ignorato.
    @Size(min = 8, message = "La password deve avere almeno 8 caratteri")
    private String password;

    private String city;
    private String address;
    private Role role;
    private String bio;
    
    // Coordinate x, y (utili per mappe o geolocalizzazione)
    private int x;
    private int y;
}