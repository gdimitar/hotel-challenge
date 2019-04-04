package com.hotelchallenge.rest;

import com.hotelchallenge.constants.RestRouter;
import com.hotelchallenge.data.LikeData;
import com.hotelchallenge.model.HotelReviews;
import com.hotelchallenge.model.User;
import com.hotelchallenge.service.LikeService;
import com.hotelchallenge.service.ReviewsService;
import com.hotelchallenge.service.UserService;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeRest {

    private final ReviewsService reviewsService;

    private final LikeService likeService;

    private final UserService userService;

    public LikeRest(final ReviewsService reviewsService, final LikeService likeService,
            final UserService userService) {
        this.reviewsService = reviewsService;
        this.likeService = likeService;
        this.userService = userService;
    }

    @PostMapping(RestRouter.Like.ADD)
    public ResponseEntity addLike(final @Valid @RequestBody LikeData likeData) {
        final Optional<HotelReviews> review = reviewsService.findReviewById(likeData.getReviewId());
        if (!review.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        final Optional<User> user = userService.findUserById(likeData.getUserId());
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        likeService.likeReview(review.get(), user.get(), likeData.getLike());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(RestRouter.Like.REMOVE)
    public ResponseEntity deleteLike(final @Valid @RequestBody LikeData likeData) {
        final Optional<HotelReviews> review = reviewsService.findReviewById(likeData.getReviewId());
        if (!review.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        final Optional<User> user = userService.findUserById(likeData.getUserId());
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        likeService.removeLikeByReviewAndUserAndLike(review.get(), user.get(), likeData.getLike());
        return ResponseEntity.ok().build();
    }
}
