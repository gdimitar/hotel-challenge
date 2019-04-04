package com.hotelchallenge.repository;

import com.hotelchallenge.model.HotelLikes;
import com.hotelchallenge.model.HotelReviews;
import com.hotelchallenge.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<HotelLikes, Long> {

    Optional<HotelLikes> findByReviewAndUserAndLikeIs(HotelReviews review, User user, boolean like);

    List<HotelLikes> findByReviewAndLikeIs(HotelReviews review, boolean like);
}
