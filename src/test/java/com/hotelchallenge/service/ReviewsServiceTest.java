package com.hotelchallenge.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.hotelchallenge.dto.ReviewDTO;
import com.hotelchallenge.mapper.HotelReviewsMapper;
import com.hotelchallenge.model.Hotel;
import com.hotelchallenge.model.HotelReviews;
import com.hotelchallenge.repository.HotelRepository;
import com.hotelchallenge.repository.ReviewsRepository;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ReviewsServiceTest {

    private static final String HOTEL_NAME = "testHotelName";
    private static final Long HOTEL_ID = 1L;

    @InjectMocks
    private ReviewsService service;

    @Mock
    private ReviewsRepository reviewsRepository;
    @Mock
    private HotelRepository hotelRepository;
    @Mock
    private HotelReviewsMapper hotelReviewsMapper;

    @Mock
    private Hotel hotel;
    @Mock
    private HotelReviews hotelReviews;
    @Mock
    private HotelReviews hotelReviews1;
    @Mock
    private HotelReviews hotelReviews2;
    @Mock
    private Pageable pageable;
    @Mock
    private Page<HotelReviews> hotelReviewsPage;

    private final ReviewDTO reviewDTO = new ReviewDTO();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        reviewDTO.setHotelName(HOTEL_NAME);
        doReturn(Optional.of(hotel)).when(hotelRepository).findByName(HOTEL_NAME);
        doReturn(Optional.of(hotel)).when(hotelRepository).findById(HOTEL_ID);

        doReturn(Arrays.asList(hotelReviews1, hotelReviews2)).when(hotelReviewsPage).getContent();
        doReturn(hotelReviewsPage).when(reviewsRepository).findAllByHotel(pageable, hotel);
    }

    @Test
    public void testAddReview() {
        doReturn(hotelReviews).when(hotelReviewsMapper).createHotelReview(reviewDTO, hotel);

        service.addReview(reviewDTO);

        verify(reviewsRepository).save(hotelReviews);
    }

    @Test
    public void testNoHotelFound() {
        doReturn(Optional.empty()).when(hotelRepository).findByName(HOTEL_NAME);

        service.addReview(reviewDTO);

        verify(reviewsRepository, never()).save(hotelReviews);
    }

    @Test
    public void testGetAllReviews() {
        final Page<HotelReviews> results = service.getAllReviews(pageable, HOTEL_ID);
        assertEquals(2, results.getContent().size());
    }

    @Test
    public void testNoReviewsFound() {
        doReturn(null).when(reviewsRepository).findAllByHotel(pageable, hotel);

        assertNull(service.getAllReviews(pageable, HOTEL_ID));
    }
}