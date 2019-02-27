package com.hotelchallenge.repository;

import com.hotelchallenge.model.FavoriteHotel;
import com.hotelchallenge.model.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoriteHotel, Long> {

    Optional<FavoriteHotel> findById(Long id);

    Page<FavoriteHotel> findAllByUser(Pageable pageable, User user);
}
