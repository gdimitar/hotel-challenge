package com.hotelchallenge.service;

import com.hotelchallenge.dto.HotelDTO;
import com.hotelchallenge.mapper.HotelMapper;
import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.repository.HotelRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Hotel createHotel(final HotelDTO hotelDTO) {
        final Hotel hotel = hotelMapper.createHotel(hotelDTO);
        hotelRepository.save(hotel);

        return hotel;
    }

    @Transactional(readOnly = true)
    public Page<Hotel> getAllHotels(final Pageable pageable) {
        return hotelRepository.findAllOrderByNameAsc(pageable);
    }

    public Optional<Hotel> editHotel(final HotelDTO hotelDTO) {
        if (hotelDTO != null && !StringUtils.isEmpty(hotelDTO.getName())) {
            final Optional<Hotel> hotel = hotelRepository.findById(hotelDTO.getId());

            if (hotel.isPresent()) {
                hotelMapper.updateHotel(hotel.get(), hotelDTO);
                hotelRepository.save(hotel.get());
                return hotel;
            }
        }

        return Optional.empty();
    }

    public Page<Hotel> search(final Pageable pageable, final String name, final String address) {
        if (!StringUtils.isEmpty(address)) {
            return hotelRepository.findAllByNameAndAddress(pageable, name, address);
        } else {
            return hotelRepository.findAllByName(pageable, name);
        }
    }
}
