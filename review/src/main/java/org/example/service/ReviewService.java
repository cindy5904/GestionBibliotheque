package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.dto.BookDto;
import org.example.dto.UserDto;
import org.example.entity.Review;
import org.example.repository.ReviewRepository;
import org.example.user.BookServiceUser;
import org.example.user.UserServiceUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ReviewService {
    @Inject
    ReviewRepository reviewRepository;

    @Inject
    @RestClient
    UserServiceUser userServiceUser;

    @Inject
    @RestClient
    BookServiceUser bookServiceUser;

    public List<Review> getAllReviews() {
        List<Review> reviews = reviewRepository.listAll();
        return reviews.stream()
                .map(this::enrichReviewWithDetails)
                .collect(Collectors.toList());
    }

    public Review getReviewById(Long id) {
        return Optional.ofNullable(reviewRepository.findById(id))
                .map(this::enrichReviewWithDetails)
                .orElseThrow(() -> new WebApplicationException("Review not found", 404));
    }

    @Transactional
    public Review createReview(Review review) {
        validateReview(review);
        review.setCommentaire(review.getCommentaire());
        review.setNote(review.getNote());
        reviewRepository.isPersistent(review);
        return enrichReviewWithDetails(review);
    }

    @Transactional
    public Review updateReview(Long id, Review review) {
        validateReview(review);

        return Optional.ofNullable(reviewRepository.findById(id))
                .map(existingReview -> {

                    existingReview.setCommentaire(review.getCommentaire());
                    existingReview.setNote(review.getNote());
                    reviewRepository.persist(existingReview);
                    return enrichReviewWithDetails(existingReview);
                })
                .orElseThrow(() -> new WebApplicationException("Review not found", 404));
    }

    @Transactional
    public void deleteReview(Long id) {
        Optional.ofNullable(reviewRepository.findById(id))
                .ifPresentOrElse(reviewRepository::delete,
                        () -> { throw new WebApplicationException("Review not found", 404); });
    }

    public List<Review> getReviewsByUserId(Long userId) {
        if (userId == null) {
            throw new WebApplicationException("User ID is required", 400);
        }

        List<Review> reviews = reviewRepository.getReviewByIdUser(userId);

        if (reviews.isEmpty()) {
            throw new WebApplicationException("No reviews found for user ID " + userId, 404);
        }

        return reviews.stream()
                .map(this::enrichReviewWithDetails)
                .collect(Collectors.toList());
    }


    private Review enrichReviewWithDetails(Review review) {
        if (review != null) {
            UserDto user = userServiceUser.getUserById(review.getUserId());
            BookDto book = bookServiceUser.getBookById(review.getBookId());

            if (user == null) {
                throw new WebApplicationException("User not found for ID " + review.getUserId(), 404);
            }

            if (book == null) {
                throw new WebApplicationException("Book not found for ID " + review.getBookId(), 404);
            }

            review.setBookDto(book);
            review.setUserDto(user);
        }
        return review;
    }

    private void validateReview(Review review) {
        if (review == null) {
            throw new WebApplicationException("Review cannot be null", 400);
        }

        if (review.getUserId() == null) {
            throw new WebApplicationException("User ID cannot be null", 400);
        }

        if (review.getBookId() == null) {
            throw new WebApplicationException("Book ID cannot be null", 400);
        }


    }
}
