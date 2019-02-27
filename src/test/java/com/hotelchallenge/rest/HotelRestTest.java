package com.hotelchallenge.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hotelchallenge.TestApplication;
import com.hotelchallenge.constants.RestRouter;
import com.hotelchallenge.dto.HotelDTO;
import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.repository.HotelRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

public class HotelRestTest extends TestApplication {

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    @Transactional
    public void testRegisterHotel() throws Exception {
        final HotelDTO hotelDTO = new HotelDTO("hotelName", "hotelAddress", "hotelImg", "hotelDescription",
                10L, 20L, 5L);

        mockMvc.perform(MockMvcRequestBuilders.post(RestRouter.Hotel.REGISTER)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(hotelDTO)))
                .andExpect(status().isCreated());

        final Optional<Hotel> hotel = hotelRepository.findByName("hotelName");
        assertThat(hotel.isPresent()).isTrue();
    }

    @Test
    @Transactional
    public void testGetAllHotels() throws Exception {
        final Hotel hotel1 = createHotel("hotelName1", "hotelAddress1", "hotelImg1", "hotelDescription1",
                101L, 220L, 3L);
        final Hotel hotel2 = createHotel("hotelName2", "hotelAddress2", "hotelImg2", "hotelDescription2",
                102L, 210L, 5L);

        mockMvc.perform(get(RestRouter.Hotel.LIST)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(Arrays.asList(hotel1, hotel2))))
                .andExpect(status().isOk());

        final List<Hotel> hotels = hotelRepository.findAll();
        assertEquals(2, hotels.size());
    }

    private Hotel createHotel(final String name, final String address, final String image, final String description,
            final Long latitude, final Long longitude, final Long rating) {
        final Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setImage(image);
        hotel.setDescription(description);
        hotel.setLatitude(latitude);
        hotel.setLongitude(longitude);
        hotel.setRating(rating);
        hotelRepository.save(hotel);

        return hotel;
    }

}