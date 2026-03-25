package com.generation.localpro.Service;

import com.generation.localpro.model.Review;
import com.generation.localpro.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    public Review update(Integer id, Review review) {
        if (!reviewRepository.existsById(id)) {
            throw new EntityNotFoundException("Recensione non trovata con id: " + id);
        }
        review.setId(id);
        return reviewRepository.save(review);
    }

    public Review getById(Integer id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recensione non trovata con id: " + id));
    }

    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    // Requires: List<Review> findByUserId(Integer userId); in the repository
    public List<Review> getByUserId(Integer userId) {
        return reviewRepository.findByUserId(userId);
    }

    public void delete(Integer id) {
        if (!reviewRepository.existsById(id)) {
            throw new EntityNotFoundException("Recensione non trovata con id: " + id);
        }
        reviewRepository.deleteById(id);
    }
}
