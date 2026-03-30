package com.generation.localpro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortalUserDTO {

    @NotBlank(message = "Il nome è obbligatorio")
    private String userName;

    @Email(message = "Inserire un indirizzo email valido")
    @NotBlank(message = "L'email è obbligatoria")
    private String email;

    @NotBlank(message = "La password è obbligatoria")
    @Size(min = 8, message = "La password deve avere almeno 8 caratteri")
    private String password;


    private String city;
    private String address;
    private List<String> roles;
    private String bio;
    private Double x;
    private Double y;
}