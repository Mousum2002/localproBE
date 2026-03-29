package com.generation.localpro.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import com.generation.localpro.dto.OperationToListDTO;
import com.generation.localpro.model.OperationTypeByVendor;
import com.generation.localpro.model.Review;
import java.util.List;



@Mapper(componentModel = "spring")
public interface OperationToListMapper {

    @Mapping(source = "user.userName",              target = "userName")
    @Mapping(source = "user.x",                     target = "x")
    @Mapping(source = "user.y",                     target = "y")
    @Mapping(source = "operationType.description",  target = "description")
    @Mapping(source = "user.reviews",               target = "ratingAvg", qualifiedByName = "calcRatingAvg")
    @Mapping(source = "user.city",                  target = "city")
    OperationToListDTO toDto(OperationTypeByVendor entity);

    List<OperationToListDTO> toDtos(List<OperationTypeByVendor> entities);

    @Named("calcRatingAvg")
    default Integer calcRatingAvg(List<Review> reviews) {
        if (reviews == null || reviews.isEmpty()) return 0;
        return (int) Math.round(
            reviews.stream()
                   .mapToInt(Review::getRating)
                   .average()
                   .orElse(0.0)
        );
    }
}