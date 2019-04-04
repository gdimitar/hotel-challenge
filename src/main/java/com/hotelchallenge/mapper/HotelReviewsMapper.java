package com.hotelchallenge.mapper;

import com.hotelchallenge.data.ReviewData;
import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.model.HotelReviews;
import org.springframework.stereotype.Component;

@Component
public class HotelReviewsMapper {

    public HotelReviews createHotelReview(final ReviewData reviewData, final Hotel hotel) {
        final HotelReviews hotelReviews = new HotelReviews();
        hotelReviews.setDescription(reviewData.getDescription());
        hotelReviews.setRating(reviewData.getRating());
        hotelReviews.setHotel(hotel);

        return hotelReviews;
    }
}
