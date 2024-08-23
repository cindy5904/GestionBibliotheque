package org.example.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.example.entity.Review;
import org.example.service.ReviewService;

import java.util.List;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource {
    @Inject
    ReviewService reviewService;

    @GET
    public List<Review> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GET
    @Path("/{id}")
    public Review getReviewById(@PathParam("id") Long id) {
        return reviewService.getReviewById(id);
    }

    @POST
    public Review createReview(Review review) {
        return reviewService.createReview(review);
    }

    @PUT
    @Path("/{id}")
    public Review updateReview(@PathParam("id") Long id, Review review) {
        return reviewService.updateReview(id, review);
    }

    @DELETE
    @Path("/{id}")
    public void deleteReview(@PathParam("id") Long id) {
        reviewService.deleteReview(id);
    }


    @GET
    @Path("/user/{userId}")
    public List<Review> getReviewsByUserId(@PathParam("userId") Long userId) {
        return reviewService.getReviewsByUserId(userId);
    }
}
