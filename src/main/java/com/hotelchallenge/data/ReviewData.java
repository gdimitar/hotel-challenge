package com.hotelchallenge.data;

public class ReviewData {

    private Long id;
    private String description;
    private Integer rating;
    private String hotelName;

    public ReviewData(final String description, final Integer rating, final String hotelName) {
        this.description = description;
        this.rating = rating;
        this.hotelName = hotelName;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(final Integer rating) {
        this.rating = rating;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(final String hotelName) {
        this.hotelName = hotelName;
    }
}
