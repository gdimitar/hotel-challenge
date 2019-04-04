package com.hotelchallenge.repository;

import com.hotelchallenge.model.FavoriteHotel;
import com.hotelchallenge.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteHotel, Long> {

    Optional<FavoriteHotel> findById(Long id);

    List<FavoriteHotel> findAllByUser(User user);
}
