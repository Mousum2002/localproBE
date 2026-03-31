package com.generation.localpro.Controller;

import com.generation.localpro.Service.ReviewService;
import com.generation.localpro.dto.ReviewDTO;
import com.generation.localpro.dto.ReviewResponseDTO;
import com.generation.localpro.mapper.ReviewMapper;
import com.generation.localpro.model.Review;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public ReviewController(ReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponseDTO  create(@Valid @RequestBody ReviewDTO dto) {
        Review created = reviewService.create(dto);
        return reviewMapper.toResponseDto(created);
    }

    @GetMapping("/my/reviews")
    public List<ReviewResponseDTO > getAll() {
        List<Review> reviews = reviewService.getAll();
        return reviews.stream().map(reviewMapper::toResponseDto).toList();
    }
    //accetta il username del vendor
    @GetMapping("/vendor/{vendorName}")
    public List<ReviewResponseDTO> getVendorReviews(@PathVariable String vendorName){
        List<Review> reviews = reviewService.getVendorReviews(vendorName);
        return reviews.stream().map(reviewMapper::toResponseDto).toList();
    }


   
}