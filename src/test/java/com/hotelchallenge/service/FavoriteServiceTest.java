package com.hotelchallenge.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.hotelchallenge.dto.FavoritesDTO;
import com.hotelchallenge.mapper.FavoriteMapper;
import com.hotelchallenge.model.FavoriteHotel;
import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.model.User;
import com.hotelchallenge.repository.FavoriteRepository;
import com.hotelchallenge.repository.HotelRepository;
import com.hotelchallenge.repository.UserRepository;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FavoriteServiceTest {

    private static final String USER_EMAIL = "test@test.com";
    private static final String HOTEL_NAME = "testHotelName";

    @InjectMocks
    private FavoriteService service;

    @Mock
    private FavoriteRepository favoriteRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private FavoriteMapper favoriteMapper;

    @Mock
    private User user;
    @Mock
    private Hotel hotel;
    @Mock
    private FavoriteHotel favoriteHotel;

    private FavoritesDTO favoritesDTO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        favoritesDTO = createFavorites();
    }

    @Test
    public void testAddHotelToFavorite() {
        doReturn(Optional.of(user)).when(userRepository).findByEmail(USER_EMAIL);
        doReturn(Optional.of(hotel)).when(hotelRepository).findByName(HOTEL_NAME);
        doReturn(favoriteHotel).when(favoriteMapper).createFavoriteHotel(user, hotel);

        final FavoriteHotel favoriteHotel = service.addHotelToFavorite(favoritesDTO);

        verify(favoriteRepository).save(favoriteHotel);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullFavorite() {
        service.addHotelToFavorite(null);
    }

    @Test
    public void testAddFavoriteHotelNoUserEmail() {
        doReturn(Optional.of(hotel)).when(hotelRepository).findByName(HOTEL_NAME);

        assertNull(service.addHotelToFavorite(favoritesDTO));
    }

    @Test
    public void testAddFavoriteHotelNoHotelName() {
        doReturn(Optional.of(user)).when(userRepository).findByEmail(USER_EMAIL);

        assertNull(service.addHotelToFavorite(favoritesDTO));
    }

    @Test
    public void testRemoveHotelFromFavorite() {
        doReturn(Optional.of(favoriteHotel)).when(favoriteRepository).findById(anyLong());

        service.removeHotelFromFavorite(anyLong());

        verify(favoriteRepository).delete(favoriteHotel);
    }

    @Test
    public void testRemoveNoFavoriteHotel() {
        doReturn(Optional.empty()).when(favoriteRepository).findById(anyLong());

        service.removeHotelFromFavorite(anyLong());

        verify(favoriteRepository, never()).delete(favoriteHotel);
    }

    private FavoritesDTO createFavorites() {
        final FavoritesDTO dto = new FavoritesDTO();
        dto.setHotelName(FavoriteServiceTest.HOTEL_NAME);
        dto.setUserEmail(FavoriteServiceTest.USER_EMAIL);

        return dto;
    }
}