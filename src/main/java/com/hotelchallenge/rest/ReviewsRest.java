package com.hotelchallenge.rest;

import com.hotelchallenge.constants.RestRouter;
import com.hotelchallenge.data.ReviewData;
import com.hotelchallenge.model.HotelReviews;
import com.hotelchallenge.service.ReviewsService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewsRest {

    private final ReviewsService reviewsService;

    public ReviewsRest(final ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @PostMapping(path = RestRouter.Reviews.ADD,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity addReview(final @Valid @RequestBody ReviewData reviewData) {
        final HotelReviews hotelReview = reviewsService.addReview(reviewData);

        return ResponseEntity.ok(hotelReview);
    }

    @GetMapping(RestRouter.Reviews.LIST)
    public ResponseEntity<List<HotelReviews>> getAllHotelReviews(final @PathVariable Long hotelId) {
        final List<HotelReviews> hotels = reviewsService.getAllReviews(hotelId);

        return ResponseEntity.ok(hotels);
    }


}
