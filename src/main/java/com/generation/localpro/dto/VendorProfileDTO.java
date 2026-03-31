package com.generation.localpro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorProfileDTO {

    private Integer id;
    private String userName;
    private String firstName;
    private String lastName;
    private String profileImage;
    private String bio;
    private String city;
    private String address;
    private Double latitude;
    private Double longitude;
    private Integer ratingAvg;
    private List<ReviewResponseDTO> reviews;
    private List<VendorServiceDTO> services;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class VendorServiceDTO {
        private Integer id;
        private String category;
        private String description;
        private Integer price;
    }
}