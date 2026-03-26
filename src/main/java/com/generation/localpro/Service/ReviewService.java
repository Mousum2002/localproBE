package com.generation.localpro.service;

import com.generation.localpro.dto.ReviewDTO;
import com.generation.localpro.model.PortalUser;
import com.generation.localpro.model.Review;
import com.generation.localpro.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import com.generation.localpro.repository.PortalUserRepository;
import com.generation.localpro.mapper.ReviewMapper;


@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PortalUserRepository portalUserRepository;
    private final ReviewMapper reviewMapper;




    public ReviewService(ReviewRepository reviewRepository, PortalUserRepository portalUserRepository,ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.portalUserRepository = portalUserRepository;
        this.reviewMapper = reviewMapper;
    }

    public Review create(ReviewDTO dto) {
    PortalUser user = portalUserRepository.findById(dto.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
    Review review = reviewMapper.toEntity(dto); 
    review.setUser(user);                       
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
