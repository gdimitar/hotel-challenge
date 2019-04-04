package com.hotelchallenge.rest;

import com.hotelchallenge.constants.RestRouter;
import com.hotelchallenge.data.FavoritesData;
import com.hotelchallenge.model.FavoriteHotel;
import com.hotelchallenge.service.FavoriteService;
import com.hotelchallenge.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

    public FavoritesRest(final FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
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
    public ResponseEntity<List<FavoriteHotel>> viewFavoriteHotels(final @ApiParam Pageable pageable,
            final @PathVariable Long userId) {
        final Page<FavoriteHotel> favoriteHotels = favoriteService.findAll(pageable, userId);

        final HttpHeaders headers = PaginationUtil
                .generatePaginationHttpHeaders(favoriteHotels, RestRouter.Favorites.VIEW);
        return new ResponseEntity<>(favoriteHotels.getContent(), headers, HttpStatus.OK);
    }
}
