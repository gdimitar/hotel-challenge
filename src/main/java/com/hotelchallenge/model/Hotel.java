package com.hotelchallenge.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hotelinformation")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;
    @Column(name = "latitude")
    private Long latitude;
    @Column(name = "longitude")
    private Long longitude;
    @Column(name = "rating")
    private Long rating;
    @OneToMany(mappedBy = "hotel", orphanRemoval = true)
    private Set<HotelReviews> reviews;

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

    public Set<HotelReviews> getReviews() {
        return reviews;
    }

    public void setReviews(final Set<HotelReviews> reviews) {
        this.reviews = reviews;
    }
}
