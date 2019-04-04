package com.hotelchallenge.rest;

import com.hotelchallenge.constants.RestRouter;
import com.hotelchallenge.data.HotelData;
import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.repository.HotelRepository;
import com.hotelchallenge.service.HotelService;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelRest {

    private final HotelRepository hotelRepository;

    private final HotelService hotelService;

    public HotelRest(final HotelRepository hotelRepository, final HotelService hotelService) {
        this.hotelRepository = hotelRepository;
        this.hotelService = hotelService;
    }

    @PostMapping(path = RestRouter.Hotel.REGISTER,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<Long> registerHotel(final @Valid @RequestBody HotelData hotelData) {
        final Optional<Hotel> hotel = hotelRepository.findByName(hotelData.getName());

        if (hotel.isPresent()) {
            return new ResponseEntity<>(hotel.get().getId(), HttpStatus.BAD_REQUEST);
        }

        final Hotel newHotel = hotelService.createHotel(hotelData);
        return new ResponseEntity<>(newHotel.getId(), HttpStatus.OK);
    }

    @GetMapping(RestRouter.Hotel.LIST)
    public ResponseEntity<List<Hotel>> getAllHotels() {
        final List<Hotel> hotels = hotelService.getAllHotels();

        return ResponseEntity.ok(hotels);
    }

    @GetMapping(RestRouter.Hotel.VIEW)
    public ResponseEntity<Hotel> viewHotel(final @PathVariable Long id) {
        final Optional<Hotel> hotel = hotelRepository.findById(id);

        return ResponseEntity.of(hotel);
    }

    @PostMapping(RestRouter.Hotel.LIST)
    public ResponseEntity<Hotel> editHotel(final @Valid @RequestBody HotelData hotelData) {
        final Optional<Hotel> hotel = hotelService.editHotel(hotelData);

        return ResponseEntity.of(hotel);
    }

    @GetMapping(RestRouter.Hotel.SEARCH)
    public ResponseEntity<List<Hotel>> searchHotels(final @PathVariable String name,
            final @PathVariable(required = false) String address) {
        final List<Hotel> hotels = hotelService.search(name, address);

        return ResponseEntity.ok(hotels);
    }
}
