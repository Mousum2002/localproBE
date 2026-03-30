package com.generation.localpro.mapper;


import com.generation.localpro.dto.ReviewDTO;
import com.generation.localpro.dto.ReviewResponseDTO;
import com.generation.localpro.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "user.id",       target = "userId")
    @Mapping(source = "user.userName", target = "userName")
    ReviewResponseDTO toResponseDto(Review entity);

    @Mapping(target = "id",   ignore = true)
    @Mapping(target = "user", ignore = true)
    Review toEntity(ReviewDTO dto);
}
