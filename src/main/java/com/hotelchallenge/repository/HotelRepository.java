package com.hotelchallenge.repository;

import com.hotelchallenge.model.Hotel;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findByName(String name);

    @Query("SELECT h from Hotel h order by h.name asc")
    Page<Hotel> findAllOrderByNameAsc(Pageable pageable);

    Page<Hotel> findAllByName(Pageable pageable, String name);

    Page<Hotel> findAllByNameAndAddress(Pageable pageable, String name, String address);
}
