package com.generation.localpro.Service;

import com.generation.localpro.dto.ReviewDTO;

import com.generation.localpro.model.PortalUser;
import com.generation.localpro.model.Review;
import com.generation.localpro.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import com.generation.localpro.mapper.ReviewMapper;


@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PortalUserService userService;
    private final ReviewMapper reviewMapper;

    public Review create(ReviewDTO dto) {
    PortalUser vendorUser = userService.getById(dto.getUserId());
    PortalUser currentUser = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
    if (vendorUser == currentUser) {
        throw new IllegalArgumentException("Non è un essercizio di autovalutazione! ");
    }
    Review review = reviewMapper.toEntity(dto); 
    review.setUser(vendorUser);                       
    return reviewRepository.save(review);
}

    

    public Review getById(Integer id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recensione non trovata con id: " + id));
    }

    public List<Review> getAll() {
        return reviewRepository.findAll().stream().filter((r)->r.getUser()== userService.findByUserName( SecurityContextHolder.getContext().getAuthentication().getName())).toList();
    }

    // Requires: List<Review> findByUserId(Integer userId); in the repository
    public List<Review> getByUserId(Integer userId) {
        return reviewRepository.findByUserId(userId);
    }

    public List<Review> getVendorReviews(String vendorName) {
        return reviewRepository.findAll().stream().filter((r)->r.getUser().getUserName().equalsIgnoreCase(vendorName)).toList();
    }

    public void deleteIfOwner(Integer reviewId, String userName) {
    Review review = reviewRepository.findById(reviewId)
        .orElseThrow(() -> new EntityNotFoundException("Recensione non trovata"));
    // chiunque può cancellare solo le proprie recensioni
    // il profilo del vendor può cancellare le recensioni su se stesso
    reviewRepository.deleteById(reviewId);
}

}
