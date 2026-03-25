package com.generation.localpro.dto;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PortalUserResponseDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    // password intentionally excluded
    private String city;
    private String address;
    private List<String> roles;
    private String bio;
    private int x;
    private int y;
}
