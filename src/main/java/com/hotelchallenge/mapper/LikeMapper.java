package com.hotelchallenge.mapper;

import com.hotelchallenge.model.HotelLikes;
import com.hotelchallenge.model.HotelReviews;
import com.hotelchallenge.model.User;
import org.springframework.stereotype.Component;

@Component
public class LikeMapper {

    public HotelLikes createHotelLike(final HotelReviews review, final User user, final boolean like) {
        final HotelLikes hotelLikes = new HotelLikes();
        hotelLikes.setReview(review);
        hotelLikes.setUser(user);
        hotelLikes.setLike(like);

        return hotelLikes;
    }
}
