package com.hotelchallenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hotellikes")
public class HotelLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "like")
    private boolean like;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private HotelReviews review;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(final boolean like) {
        this.like = like;
    }

    public HotelReviews getReview() {
        return review;
    }

    public void setReview(final HotelReviews review) {
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
