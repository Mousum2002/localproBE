package com.generation.localpro.Service;

import java.util.List;

import com.generation.localpro.model.Review;

public interface ReviewService {

    Review create(Review review);

    Review update(Integer id, Review review);

    Review getById(Integer id);

    List<Review> getAll();

    List<Review> getByUserId(Integer userId);

    void delete(Integer id);
}
