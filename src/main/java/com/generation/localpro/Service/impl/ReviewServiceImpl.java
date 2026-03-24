package com.generation.localpro.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generation.localpro.Service.ReviewService;
import com.generation.localpro.exception.ResourceNotFoundException;
import com.generation.localpro.model.Review;
import com.generation.localpro.repository.ReviewRepository;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review update(Integer id, Review review) {
        Review existing = getById(id);
        existing.setUserId(review.getUserId());
        existing.setRating(review.getRating());
        existing.setDescription(review.getDescription());
        return reviewRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public Review getById(Integer id) {
        return reviewRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Review> getByUserId(Integer userId) {
        return reviewRepository.findByUserId(userId);
    }

    @Override
    public void delete(Integer id) {
        Review existing = getById(id);
        reviewRepository.delete(existing);
    }
}
