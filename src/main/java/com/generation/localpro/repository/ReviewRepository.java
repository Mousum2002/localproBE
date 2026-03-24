package com.generation.localpro.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.localpro.model.Review;

 public interface ReviewRepository extends JpaRepository<Review, Integer>
{
    List<Review> findByUserId(Integer userId);
   
    List<Review> findByRating(String rating);
}
