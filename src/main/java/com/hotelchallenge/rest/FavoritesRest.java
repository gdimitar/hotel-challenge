package com.hotelchallenge.rest;

import com.hotelchallenge.constants.RestRouter;
import com.hotelchallenge.data.FavoritesData;
import com.hotelchallenge.model.FavoriteHotel;
import com.hotelchallenge.model.User;
import com.hotelchallenge.service.FavoriteService;
import com.hotelchallenge.service.UserService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FavoritesRest {

    private final FavoriteService favoriteService;

    private final UserService userService;

    public FavoritesRest(final FavoriteService favoriteService, final UserService userService) {
        this.favoriteService = favoriteService;
        this.userService = userService;
    }

    @PostMapping(path = RestRouter.Favorites.ADD,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<FavoriteHotel> addToFavorite(final @Valid @RequestBody FavoritesData favoritesData) {
        return ResponseEntity.ok().body(favoriteService.addHotelToFavorite(favoritesData));
    }

    @PostMapping(path = RestRouter.Favorites.REMOVE,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Void> removeFromFavorite(final @PathVariable Long id) {
        favoriteService.removeHotelFromFavorite(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(RestRouter.Favorites.VIEW)
    public ResponseEntity<List<FavoriteHotel>> viewFavoriteHotels(final @PathVariable Long userId) {
        final Optional<User> user = userService.findUserById(userId);
        if (!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        final List<FavoriteHotel> favoriteHotels = favoriteService.findAll(userId);

        return ResponseEntity.ok(favoriteHotels);
    }
}
