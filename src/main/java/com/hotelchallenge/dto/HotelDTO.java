package com.hotelchallenge.dto;

public class HotelDTO {

    private Long id;
    private String name;
    private String address;
    private String image;
    private String description;
    private Long latitude;
    private Long longitude;
    private Long rating;

    public HotelDTO() {

    }

    public HotelDTO(final String name, final String address, final String image,
            final String description, final Long latitude, final Long longitude, final Long rating) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(final Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(final Long longitude) {
        this.longitude = longitude;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(final Long rating) {
        this.rating = rating;
    }
}
