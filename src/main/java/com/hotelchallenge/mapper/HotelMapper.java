package com.hotelchallenge.mapper;

import com.hotelchallenge.dto.HotelDTO;
import com.hotelchallenge.model.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    public Hotel createHotel(final HotelDTO hotelDTO) {
        final Hotel hotel = new Hotel();
        setFields(hotel, hotelDTO);

        return hotel;
    }

    public void updateHotel(final Hotel hotel, final HotelDTO hotelDTO) {
        setFields(hotel, hotelDTO);

    }

    private void setFields(final Hotel hotel, final HotelDTO hotelDTO) {
        hotel.setName(hotelDTO.getName());
        hotel.setAddress(hotelDTO.getAddress());
        hotel.setDescription(hotelDTO.getDescription());
        hotel.setImage(hotelDTO.getImage());
        hotel.setLatitude(hotelDTO.getLatitude());
        hotel.setLongitude(hotelDTO.getLongitude());
        hotel.setRating(hotelDTO.getRating());
    }
}
