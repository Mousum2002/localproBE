package com.generation.localpro.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.generation.localpro.Service.ReviewService;
import com.generation.localpro.dto.ReviewDTO;
import com.generation.localpro.mapper.ReviewMapper;
import com.generation.localpro.model.Review;

import jakarta.validation.Valid;

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
    public ReviewDTO create(@Valid @RequestBody ReviewDTO reviewDto) {
        Review created = reviewService.create(reviewMapper.toEntity(reviewDto));
        return reviewMapper.toDto(created);
    }

    @PutMapping("/{id}")
    public ReviewDTO update(@PathVariable Integer id, @Valid @RequestBody ReviewDTO reviewDto) {
        Review updated = reviewService.update(id, reviewMapper.toEntity(reviewDto));
        return reviewMapper.toDto(updated);
    }

    @GetMapping("/{id}")
    public ReviewDTO getById(@PathVariable Integer id) {
        return reviewMapper.toDto(reviewService.getById(id));
    }

    @GetMapping
    public List<ReviewDTO> getAll(@RequestParam(required = false) Integer userId) {
        List<Review> reviews;
        if (userId != null) {
            reviews = reviewService.getByUserId(userId);
        } else {
            reviews = reviewService.getAll();
        }
        return reviews.stream().map(reviewMapper::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        reviewService.delete(id);
    }
}
