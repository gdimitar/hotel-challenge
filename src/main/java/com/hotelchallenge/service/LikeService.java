package com.hotelchallenge.service;

import com.hotelchallenge.mapper.LikeMapper;
import com.hotelchallenge.model.HotelLikes;
import com.hotelchallenge.model.HotelReviews;
import com.hotelchallenge.model.User;
import com.hotelchallenge.repository.LikeRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LikeService {

    private final LikeRepository likeRepository;

    private final LikeMapper likeMapper;

    public LikeService(final LikeRepository likeRepository, final LikeMapper likeMapper) {
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
    }

    public void likeReview(final HotelReviews review, final User user, final boolean like) {
        final Optional<HotelLikes> hotelLike = likeRepository
                .findByReviewAndUserAndLikeIs(review, user, like);

        if (hotelLike.isPresent()) {
            return;
        }

        final HotelLikes hotelLikes = likeMapper.createHotelLike(review, user, like);
        likeRepository.save(hotelLikes);
    }

    public void removeLikeByReviewAndUserAndLike(final HotelReviews review, final User user, final boolean like) {
        final Optional<HotelLikes> foundLike = likeRepository.findByReviewAndUserAndLikeIs(review, user, like);
        foundLike.ifPresent(likeRepository::delete);
    }

    public Set<User> findUsersWhoLikedReview(final HotelReviews review, final boolean like) {
        final List<HotelLikes> likes = likeRepository.findByReviewAndLikeIs(review, like);
        return likes.stream().map(HotelLikes::getUser).collect(Collectors.toSet());
    }
}
