package com.generation.localpro.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortalUserRequestDTO {
      @NotBlank(message = "Il nome è obbligatorio")
    private String userName;
    
    @Email(message = "Inserire un indirizzo email valido")
    @NotBlank(message = "L'email è obbligatoria")
    private String email;

    @NotBlank(message = "La password è obbligatoria")
    @Size(min = 8, message = "La password deve avere almeno 8 caratteri")
    private String password;

    @NotNull(message = "La x è obbligatoria")
     private Integer  x;
     @NotNull(message = "La y è obbligatoria")
    private Integer   y;

    private String firstName;
    private String lastName;
    private String profileImage;
    private String city;
    private String address;
    private String bio;
   
}
