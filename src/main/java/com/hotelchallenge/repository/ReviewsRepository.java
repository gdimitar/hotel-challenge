package com.hotelchallenge.repository;

import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.model.HotelReviews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<HotelReviews, Long> {

    Page<HotelReviews> findAllByHotel(Pageable pageable, Hotel hotel);
}
