package com.hotelchallenge.mapper;

import com.hotelchallenge.data.HotelData;
import com.hotelchallenge.model.Hotel;
import org.springframework.stereotype.Component;

@Component
public class HotelMapper {

    public Hotel createHotel(final HotelData hotelData) {
        final Hotel hotel = new Hotel();
        setFields(hotel, hotelData);

        return hotel;
    }

    public void updateHotel(final Hotel hotel, final HotelData hotelData) {
        setFields(hotel, hotelData);

    }

    private void setFields(final Hotel hotel, final HotelData hotelData) {
        hotel.setName(hotelData.getName());
        hotel.setAddress(hotelData.getAddress());
        hotel.setDescription(hotelData.getDescription());
        hotel.setImage(hotelData.getImage());
        hotel.setLatitude(hotelData.getLatitude());
        hotel.setLongitude(hotelData.getLongitude());
        hotel.setRating(hotelData.getRating());
    }
}
