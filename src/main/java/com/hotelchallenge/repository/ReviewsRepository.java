package com.hotelchallenge.repository;

import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.model.HotelReviews;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<HotelReviews, Long> {

    List<HotelReviews> findAllByHotel(Hotel hotel);
}
