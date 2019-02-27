package com.hotelchallenge.rest;

import com.hotelchallenge.constants.RestRouter;
import com.hotelchallenge.dto.HotelDTO;
import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.repository.HotelRepository;
import com.hotelchallenge.service.HotelService;
import com.hotelchallenge.util.PaginationUtil;
import com.hotelchallenge.util.ResponseUtil;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.Optional;
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
public class HotelRest {

    private final HotelRepository hotelRepository;

    private final HotelService hotelService;

    public HotelRest(final HotelRepository hotelRepository, final HotelService hotelService) {
        this.hotelRepository = hotelRepository;
        this.hotelService = hotelService;
    }

    @PostMapping(path = RestRouter.Hotel.REGISTER,
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity registerHotel(final @Valid @RequestBody HotelDTO hotelDTO) {
        final HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

        return hotelRepository.findByName(hotelDTO.getName())
                .map(hotel -> new ResponseEntity<>("hotel is already created", textPlainHeaders,
                        HttpStatus.BAD_REQUEST))
                .orElseGet(() -> {
                    hotelService.createHotel(hotelDTO);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                });
    }

    @GetMapping(RestRouter.Hotel.LIST)
    public ResponseEntity<List<Hotel>> getAllHotels(final @ApiParam Pageable pageable) {
        final Page<Hotel> hotels = hotelService.getAllHotels(pageable);

        final HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(hotels, RestRouter.Hotel.LIST);
        return new ResponseEntity<>(hotels.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping(RestRouter.Hotel.VIEW)
    public ResponseEntity<Hotel> viewHotel(final @PathVariable Long id) {
        final Optional<Hotel> hotel = hotelRepository.findById(id);

        return ResponseUtil.wrapOrNotFound(hotel);
    }

    @PostMapping(RestRouter.Hotel.LIST)
    public ResponseEntity<Hotel> editHotel(final @Valid @RequestBody HotelDTO hotelDTO) {
        final Hotel hotel = hotelService.editHotel(hotelDTO);

        return ResponseEntity.ok().body(hotel);
    }

    @GetMapping(RestRouter.Hotel.SEARCH)
    public ResponseEntity<List<Hotel>> searchHotels(final @ApiParam Pageable pageable, final @PathVariable String name,
            final @PathVariable(required = false) String address) {
        final Page<Hotel> hotels = hotelService.search(pageable, name, address);

        final HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(hotels, RestRouter.Hotel.LIST);
        return new ResponseEntity<>(hotels.getContent(), headers, HttpStatus.OK);
    }
}
