package com.hotelchallenge.rest;

import com.hotelchallenge.constants.RestRouter;
import com.hotelchallenge.dto.ReviewDTO;
import com.hotelchallenge.model.HotelReviews;
import com.hotelchallenge.service.ReviewsService;
import com.hotelchallenge.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity addReview(final @Valid @RequestBody ReviewDTO reviewDTO) {

        return ResponseEntity.ok().body(reviewsService.addReview(reviewDTO));
    }

    @GetMapping(RestRouter.Reviews.LIST)
    public ResponseEntity<List<HotelReviews>> getAllHotelReviews(final @ApiParam Pageable pageable,
            final @PathVariable Long hotelId) {
        final Page<HotelReviews> hotels = reviewsService.getAllReviews(pageable, hotelId);

        final HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(hotels, RestRouter.Reviews.LIST);
        return new ResponseEntity<>(hotels.getContent(), headers, HttpStatus.OK);
    }

}
