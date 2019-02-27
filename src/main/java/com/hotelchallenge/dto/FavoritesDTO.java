package com.hotelchallenge.dto;

public class FavoritesDTO {

    private String userEmail;
    private String hotelName;

    public FavoritesDTO() {

    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(final String userEmail) {
        this.userEmail = userEmail;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(final String hotelName) {
        this.hotelName = hotelName;
    }
}
