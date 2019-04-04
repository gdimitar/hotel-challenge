package com.hotelchallenge.service;

import com.hotelchallenge.data.ReviewData;
import com.hotelchallenge.mapper.HotelReviewsMapper;
import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.model.HotelReviews;
import com.hotelchallenge.repository.HotelRepository;
import com.hotelchallenge.repository.ReviewsRepository;
import java.util.List;
import java.util.Optional;
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

    public HotelReviews addReview(final ReviewData reviewData) {
        final String hotelName = reviewData.getHotelName();
        final Optional<Hotel> hotel = hotelRepository.findByName(hotelName);

        hotel.map(h -> {
            final HotelReviews hotelReview = hotelReviewsMapper.createHotelReview(reviewData, h);
            reviewsRepository.save(hotelReview);
            return hotelReview;
        });

        return null;
    }

    @Transactional(readOnly = true)
    public List<HotelReviews> getAllReviews(final Long hotelId) {
        final Optional<Hotel> hotel = hotelRepository.findById(hotelId);

        return hotel.map(reviewsRepository::findAllByHotel).orElse(null);
    }

    public Optional<HotelReviews> findReviewById(final long reviewId) {
        return reviewsRepository.findById(reviewId);
    }
}
