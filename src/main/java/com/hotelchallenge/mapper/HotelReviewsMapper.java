package com.hotelchallenge.mapper;

import com.hotelchallenge.dto.ReviewDTO;
import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.model.HotelReviews;
import org.springframework.stereotype.Component;

@Component
public class HotelReviewsMapper {

    public HotelReviews createHotelReview(final ReviewDTO reviewDTO, final Hotel hotel) {
        final HotelReviews hotelReviews = new HotelReviews();
        hotelReviews.setDescription(reviewDTO.getDescription());
        hotelReviews.setRating(reviewDTO.getRating());
        hotelReviews.setHotel(hotel);

        return hotelReviews;
    }
}
