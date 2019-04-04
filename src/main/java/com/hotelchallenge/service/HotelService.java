package com.hotelchallenge.service;

import com.hotelchallenge.data.HotelData;
import com.hotelchallenge.mapper.HotelMapper;
import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.repository.HotelRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class HotelService {

    private final HotelRepository hotelRepository;

    private final HotelMapper hotelMapper;

    public HotelService(final HotelRepository hotelRepository, final HotelMapper hotelMapper) {
        this.hotelRepository = hotelRepository;
        this.hotelMapper = hotelMapper;
    }

    public Hotel createHotel(final HotelData hotelData) {
        final Hotel hotel = hotelMapper.createHotel(hotelData);
        hotelRepository.save(hotel);

        return hotel;
    }

    @Transactional(readOnly = true)
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAllOrderByNameAsc();
    }

    public Optional<Hotel> editHotel(final HotelData hotelData) {
        if (hotelData != null && !StringUtils.isEmpty(hotelData.getName())) {
            final Optional<Hotel> hotel = hotelRepository.findById(hotelData.getId());

            if (hotel.isPresent()) {
                hotelMapper.updateHotel(hotel.get(), hotelData);
                hotelRepository.save(hotel.get());
                return hotel;
            }
        }

        return Optional.empty();
    }

    public List<Hotel> search(final String name, final String address) {
        if (!StringUtils.isEmpty(address)) {
            return hotelRepository.findAllByNameAndAddress(name, address);
        } else {
            return hotelRepository.findAllByName(name);
        }
    }
}
