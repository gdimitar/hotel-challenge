package com.hotelchallenge.repository;

import com.hotelchallenge.model.Hotel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Optional<Hotel> findByName(String name);

    @Query("SELECT h from Hotel h order by h.name asc")
    List<Hotel> findAllOrderByNameAsc();

    List<Hotel> findAllByName(String name);

    List<Hotel> findAllByNameAndAddress(String name, String address);
}
