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
    private String profileImage;
    private String userName;
    private String email;

    private String city;
    private String address;
    private List<String> roles;
    private String bio;
    private Double x;
    private Double y;
    private boolean isBanned;
}
