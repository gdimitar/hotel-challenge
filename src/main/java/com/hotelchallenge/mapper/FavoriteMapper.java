package com.hotelchallenge.mapper;

import com.hotelchallenge.model.FavoriteHotel;
import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.model.User;
import org.springframework.stereotype.Component;

@Component
public class FavoriteMapper {

    public FavoriteHotel createFavoriteHotel(final User user, final Hotel hotel) {
        final FavoriteHotel favoriteHotel = new FavoriteHotel();
        favoriteHotel.setUser(user);
        favoriteHotel.setHotel(hotel);

        return favoriteHotel;
    }

}
