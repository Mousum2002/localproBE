package com.generation.localpro.Controller;

import com.generation.localpro.Service.ReviewService;
import com.generation.localpro.dto.ReviewDTO;
import com.generation.localpro.dto.ReviewResponseDTO;
import com.generation.localpro.mapper.ReviewMapper;
import com.generation.localpro.model.Review;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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

    // Crea una recensione su un altro utente
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewResponseDTO create(@Valid @RequestBody ReviewDTO dto) {
        Review created = reviewService.create(dto);
        return reviewMapper.toResponseDto(created);
    }

    // Le mie recensioni scritte
    @GetMapping("/my/reviews")
    public List<ReviewResponseDTO> getMyReviews() {
        return reviewService.getAll().stream().map(reviewMapper::toResponseDto).toList();
    }

    // Tutte le recensioni ricevute da un vendor (per il suo profilo pubblico)
    @GetMapping("/vendor/{vendorName}")
    public List<ReviewResponseDTO> getVendorReviews(@PathVariable String vendorName) {
        return reviewService.getVendorReviews(vendorName)
                .stream().map(reviewMapper::toResponseDto).toList();
    }

    // Cancella una recensione — solo chi l'ha scritta O il vendor recensito
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id, Authentication auth) {
        reviewService.deleteIfOwner(id, auth.getName());
    }
}