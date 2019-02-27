package com.hotelchallenge.service;

import com.hotelchallenge.dto.ReviewDTO;
import com.hotelchallenge.mapper.HotelReviewsMapper;
import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.model.HotelReviews;
import com.hotelchallenge.repository.HotelRepository;
import com.hotelchallenge.repository.ReviewsRepository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewsService {

    private final ReviewsRepository reviewsRepository;

    private final HotelRepository hotelRepository;

    private final HotelReviewsMapper hotelReviewsMapper;

    public ReviewsService(final ReviewsRepository reviewsRepository, final HotelRepository hotelRepository,
            final HotelReviewsMapper hotelReviewsMapper) {
        this.reviewsRepository = reviewsRepository;
        this.hotelRepository = hotelRepository;
        this.hotelReviewsMapper = hotelReviewsMapper;
    }

    public HotelReviews addReview(final ReviewDTO reviewDTO) {
        final String hotelName = reviewDTO.getHotelName();
        final Optional<Hotel> hotel = hotelRepository.findByName(hotelName);

        hotel.map(h -> {
            final HotelReviews hotelReview = hotelReviewsMapper.createHotelReview(reviewDTO, h);
            reviewsRepository.save(hotelReview);
            return hotelReview;
        });

        return null;
    }

    @Transactional(readOnly = true)
    public Page<HotelReviews> getAllReviews(final Pageable pageable, final Long hotelId) {
        final Optional<Hotel> hotel = hotelRepository.findById(hotelId);

        return hotel.map(h -> reviewsRepository.findAllByHotel(pageable, h)).orElse(null);
    }
}
