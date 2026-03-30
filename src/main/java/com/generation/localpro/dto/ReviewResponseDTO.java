package com.generation.localpro.dto;

public record ReviewResponseDTO(
   Integer id,
    Integer userId,
    String userName,
    int rating,
    String description
) {}
