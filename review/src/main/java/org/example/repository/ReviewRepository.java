package org.example.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.example.entity.Review;

import java.util.List;

public class ReviewRepository implements PanacheRepository<Review> {
    public List<Review> getReviewByIdUser(Long userId) {
        return find("userId", userId).list();
    }
}
