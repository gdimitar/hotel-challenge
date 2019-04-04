package com.hotelchallenge.data;

public class FavoritesData {

    private String userEmail;
    private String hotelName;

    public FavoritesData(final String userEmail, final String hotelName) {
        this.userEmail = userEmail;
        this.hotelName = hotelName;
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
