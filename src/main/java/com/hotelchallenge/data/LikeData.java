package com.hotelchallenge.data;

public class LikeData {

    private Long reviewId;
    private Long userId;
    private Boolean like;

    public LikeData(final Long reviewId, final Long userId, final Boolean like) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.like = like;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(final Long reviewId) {
        this.reviewId = reviewId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(final Boolean like) {
        this.like = like;
    }
}
