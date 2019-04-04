package com.hotelchallenge.service;

import com.hotelchallenge.data.FavoritesData;
import com.hotelchallenge.mapper.FavoriteMapper;
import com.hotelchallenge.model.FavoriteHotel;
import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.model.User;
import com.hotelchallenge.repository.FavoriteRepository;
import com.hotelchallenge.repository.HotelRepository;
import com.hotelchallenge.repository.UserRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final FavoriteMapper favoriteMapper;
    private final UserRepository userRepository;
    private final HotelRepository hotelRepository;

    public FavoriteService(final FavoriteRepository favoriteRepository, final FavoriteMapper favoriteMapper,
            final UserRepository userRepository, final HotelRepository hotelRepository) {
        this.favoriteRepository = favoriteRepository;
        this.favoriteMapper = favoriteMapper;
        this.userRepository = userRepository;
        this.hotelRepository = hotelRepository;
    }

    public FavoriteHotel addHotelToFavorite(final FavoritesData favoritesData) {
        if (favoritesData == null) {
            throw new IllegalArgumentException("Cannot add hotel to the list of favorite hotels!");
        }

        final Optional<User> user = userRepository.findByEmail(favoritesData.getUserEmail());
        final Optional<Hotel> hotel = hotelRepository.findByName(favoritesData.getHotelName());

        if (user.isPresent() && hotel.isPresent()) {
            final FavoriteHotel favoriteHotel = favoriteMapper.createFavoriteHotel(user.get(), hotel.get());
            favoriteRepository.save(favoriteHotel);

            return favoriteHotel;
        }

        return null;
    }

    public void removeHotelFromFavorite(final Long id) {
        final Optional<FavoriteHotel> favoriteHotel = favoriteRepository.findById(id);

        favoriteHotel.ifPresent(favoriteRepository::delete);
    }

    public Page<FavoriteHotel> findAll(final Pageable pageable, final Long userId) {
        final User user = userRepository.getOne(userId);
        return favoriteRepository.findAllByUser(pageable, user);
    }
}
