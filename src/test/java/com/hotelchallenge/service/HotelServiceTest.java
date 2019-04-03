package com.hotelchallenge.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.hotelchallenge.dto.HotelDTO;
import com.hotelchallenge.mapper.HotelMapper;
import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.repository.HotelRepository;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class HotelServiceTest {

    private static final String HOTEL_NAME = "hotelName";

    @InjectMocks
    private HotelService hotelService;

    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private HotelMapper hotelMapper;

    @Mock
    private Hotel hotel;

    private final HotelDTO hotelDTO = new HotelDTO();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateHotel() {
        doReturn(hotel).when(hotelMapper).createHotel(hotelDTO);

        hotelService.createHotel(hotelDTO);

        verify(hotelRepository).save(hotel);
    }

    @Test
    public void testEditHotel() {
        doReturn(Optional.of(hotel)).when(hotelRepository).findById(hotelDTO.getId());
        hotelDTO.setName(HOTEL_NAME);

        hotelService.editHotel(hotelDTO);

        verify(hotelRepository).save(hotel);
    }

    @Test
    public void testEditHotelEmptyHotelName() {
        assertEquals(Optional.empty(), hotelService.editHotel(hotelDTO));
    }
}