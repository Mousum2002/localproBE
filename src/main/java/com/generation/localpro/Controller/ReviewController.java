package com.generation.localpro.Controller;

import com.generation.localpro.Service.ReviewService;
import com.generation.localpro.dto.ReviewDTO;
import com.generation.localpro.mapper.ReviewMapper;
import com.generation.localpro.model.Review;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


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
    public ReviewDTO create(@Valid @RequestBody ReviewDTO dto) {
        Review created = reviewService.create(dto);
        return reviewMapper.toDto(created);
    }

    @PutMapping("/{id}")
    public ReviewDTO update(@PathVariable Integer id, @Valid @RequestBody ReviewDTO dto) {
        Review updated = reviewService.update(id, reviewMapper.toEntity(dto));
        return reviewMapper.toDto(updated);
    }

    @GetMapping("/{id}")
    public ReviewDTO getById(@PathVariable Integer id) {
        return reviewMapper.toDto(reviewService.getById(id));
    }

    @GetMapping
    public List<ReviewDTO> getAll(@RequestParam(required = false) Integer userId) {
        List<Review> reviews = (userId != null)
                ? reviewService.getByUserId(userId)
                : reviewService.getAll();
        return reviews.stream().map(reviewMapper::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        reviewService.delete(id);
    }
}