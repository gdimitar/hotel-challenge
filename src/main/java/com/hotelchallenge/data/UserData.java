package com.hotelchallenge.data;

public class UserData {

    private Long id;
    private String email;
    private String displayName;
    private String password;

    public UserData(final String email, final String displayName, final String password) {
        this.email = email;
        this.displayName = displayName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
