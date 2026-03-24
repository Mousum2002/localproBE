package com.generation.localpro.mapper;

import org.springframework.stereotype.Component;

import com.generation.localpro.dto.ReviewDTO;
import com.generation.localpro.model.Review;

@Component
public class ReviewMapper {

    public ReviewDTO toDto(Review entity) {
        if (entity == null) {
            return null;
        }
        return ReviewDTO.builder()
            .id(entity.getId())
            .userId(entity.getUserId())
            .rating(entity.getRating())
            .description(entity.getDescription())
            .build();
    }

    public Review toEntity(ReviewDTO dto) {
        if (dto == null) {
            return null;
        }
        Review entity = new Review();
        entity.setId(dto.getId());
        entity.setUserId(dto.getUserId());
        entity.setRating(dto.getRating());
        entity.setDescription(dto.getDescription());
        return entity;
    }
}
